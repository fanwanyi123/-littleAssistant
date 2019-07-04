package com.fanyi.assistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Administrator
 * @Date 2019/6/1521:38
 * @Version 1.0.1
 **/
@Controller
@RequestMapping(value = "admin")
public class AdminIndexController {


    @RequestMapping(value = "index")
    public String adminIndex() {
        return "admin/index";

    }
}
