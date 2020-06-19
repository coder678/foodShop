package com.school.foodShop.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class FrontPage {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    private String index() {
        return "redirect:frontend/index";
    }
}
