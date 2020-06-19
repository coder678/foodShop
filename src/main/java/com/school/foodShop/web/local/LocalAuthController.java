package com.school.foodShop.web.local;

import com.school.foodShop.dao.LocalAuthDao;
import com.school.foodShop.dto.LocalAuthExecution;
import com.school.foodShop.entity.LocalAuth;
import com.school.foodShop.entity.PersonInfo;
import com.school.foodShop.enums.LocalAuthStateEnum;
import com.school.foodShop.exceptions.LocalAuthOperationException;
import com.school.foodShop.service.LocalAuthService;
import com.school.foodShop.service.impl.LocalAuthServiceImpl;
import com.school.foodShop.util.CodeUtil;
import com.school.foodShop.util.HttpServletRequestUtil;
import com.school.foodShop.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/local")
public class LocalAuthController {

    @Autowired
    private LocalAuthService localAuthService;

    @RequestMapping(value = "/insertLocalAuth")
    @ResponseBody
    public Map<String, Object> insertLocalAuth(HttpServletRequest request) {

        Map<String, Object> modleMap = new HashMap<>();

        if (!CodeUtil.checkVerifyCode(request)) {
            modleMap.put("success", false);
            modleMap.put("errMsg", "输入了错误的验证码");
            return modleMap;
        }

        // 获取输入的帐号
        String username = HttpServletRequestUtil.getString(request, "userName");
        // 获取输入的密码
        String password = HttpServletRequestUtil.getString(request, "password");
        if (username != null && password != null) {
            try {
                LocalAuth localAuth = new LocalAuth();
                PersonInfo personInfo = new PersonInfo();
                personInfo.setUserId(1L);
                localAuth.setUsername(username);
                localAuth.setPassword(MD5.getMd5(password));
                localAuth.setPersonInfo(personInfo);
                localAuth.setCreateTime(new Date());
                localAuthService.insertAuth(localAuth);
                modleMap.put("success",true);
            }
            catch (LocalAuthOperationException e) {
                modleMap.put("eMsg",e.toString());
            }
        }
        return modleMap;
    }
/*@RequestMapping(value = "/insertLocalAuth")
@ResponseBody
public Map<String, Object> insertLocalAuth(String username,String password) {
    Map<String, Object> modleMap = new HashMap<>();
    if (username != null && password != null) {
        try {
            LocalAuth localAuth = new LocalAuth();
            PersonInfo personInfo = new PersonInfo();
            personInfo.setUserId(1L);
            localAuth.setUsername(username);
            localAuth.setPassword(MD5.getMd5(password));
            localAuth.setCreateTime(new Date());
            localAuth.setPersonInfo(personInfo);
            localAuthService.insertAuth(localAuth);
            modleMap.put("success",true);
        }
        catch (LocalAuthOperationException e) {
            modleMap.put("eMsg",e.toString());
        }
    }
    return modleMap;
 }*/



    @RequestMapping(value = "/logincheck", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> logincheck(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        // 获取是否需要进行验证码校验的标识符
        boolean needVerify = HttpServletRequestUtil.getBoolean(request, "needVerify");
        if (needVerify && !CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }
        // 获取输入的帐号
        String userName = HttpServletRequestUtil.getString(request, "userName");
        // 获取输入的密码
        String password = HttpServletRequestUtil.getString(request, "password");
        // 非空校验
        if (userName != null && password != null) {
            // 传入帐号和密码去获取平台帐号信息
            LocalAuth localAuth = localAuthService.getLocalAuthByUsernameAndPwd(userName, password);
            if (localAuth != null) {
                // 若能取到帐号信息则登录成功
                modelMap.put("success", true);
                // 同时在session里设置用户信息
                request.getSession().setAttribute("user", localAuth.getPersonInfo());
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", "用户名或密码错误");
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "用户名和密码均不能为空");
        }
        return modelMap;
    }

    @RequestMapping(value = "/changelocalpwd", method = RequestMethod.POST)
    @ResponseBody
    /**
     * 修改密码
     *
     * @param request
     * @return
     */
    private Map<String, Object> changeLocalPwd(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        // 验证码校验
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }
        // 获取帐号
        String userName = HttpServletRequestUtil.getString(request, "userName");
        // 获取原密码
        String password = HttpServletRequestUtil.getString(request, "password");
        // 获取新密码
        String newPassword = HttpServletRequestUtil.getString(request, "newPassword");
        // 从session中获取当前用户信息(用户一旦通过微信登录之后，便能获取到用户的信息)
        PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
        // 非空判断，要求帐号新旧密码以及当前的用户session非空，且新旧密码不相同
        if (userName != null && password != null && newPassword != null && user != null && user.getUserId() != null
                && !password.equals(newPassword)) {
            try {
                // 查看原先帐号，看看与输入的帐号是否一致，不一致则认为是非法操作
                LocalAuth localAuth = localAuthService.getLocalAuthByUserId(user.getUserId());
                if (localAuth == null || !localAuth.getUsername().equals(userName)) {
                    // 不一致则直接退出
                    modelMap.put("success", false);
                    modelMap.put("errMsg", "输入的帐号非本次登录的帐号");
                    return modelMap;
                }
                // 修改平台帐号的用户密码
                LocalAuthExecution le = localAuthService.modifyLocalAuth(user.getUserId(), userName, password,
                        newPassword);
                if (le.getState() == LocalAuthStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", le.getStateInfo());
                }
            } catch (LocalAuthOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }

        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入密码");
        }
        return modelMap;
    }
}
