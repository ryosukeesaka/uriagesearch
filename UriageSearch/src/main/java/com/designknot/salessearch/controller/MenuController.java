

package com.designknot.salessearch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.designknot.salessearch.form.ReturnForm;


@Controller
public class MenuController {

    //メニューを表示
    @RequestMapping("/")
    public ModelAndView index(ModelAndView mav,@ModelAttribute ReturnForm returnForm) {


        mav.addObject("returnForm", new ReturnForm());
        mav.setViewName("index");
        return mav;
    }



}
