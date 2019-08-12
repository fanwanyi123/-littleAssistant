
package com.fanyi.assistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {
    @RequestMapping(value = "login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "takeNotes")
    public String insertArticleView() {
        return "recordInsert";
    }

    @RequestMapping(value = "album")
    public String plan() {
        return "album";
    }

    @RequestMapping(value = "index")
    public String index() {
        return "index";
    }

}