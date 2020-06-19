package com.school.foodShop.web.local;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/local")
public class keepAuth {
    @RequestMapping(value = "/Cookie")
    @ResponseBody
    public Map<String,Object> Cookie(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        String userName = (String) request.getAttribute("userName");
        String password = (String) request.getAttribute("password");
        System.out.println(userName);
        System.out.println(password);
        httpSession.setAttribute("userName",request.getAttribute("userName"));
        httpSession.setAttribute("password",request.getAttribute("password"));

        Map<String, Object> modleMap = new HashMap<>();
        modleMap.put("userName",httpSession.getAttribute("userName"));
        modleMap.put("password",httpSession.getAttribute("password"));
        System.out.println(modleMap);
        return modleMap;
    }
}
