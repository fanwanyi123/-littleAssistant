
package com.fanyi.assistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {
    @RequestMapping(value = "login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "record")
    public String record() {
        return "record";
    }

    @RequestMapping(value = "index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "expressInfo")
    public String expressInfo() {
        return "expressInfo";

    }

}