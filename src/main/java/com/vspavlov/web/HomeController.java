package com.vspavlov.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Vasiliy on 08.06.2015.
 */
@Controller
public class HomeController {


    @RequestMapping(value = "/")
    @ResponseBody
    public String rest(){
        return "Hello Energy Meter";
    }

}
