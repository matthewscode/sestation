package com.motionpoint.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by Matthew on 7/27/2016.
 */
@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping("/")
    public String indexHome() throws Exception{

        return "index.html";
    }

    @RequestMapping("/analysis/final/*")
    public String analysisPage(){
        return "site-analysis.html";
    }

    @RequestMapping("/signin")
    public String login(){
        return "login.html";
    }


}
