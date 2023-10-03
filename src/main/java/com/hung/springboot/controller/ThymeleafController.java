package com.hung.springboot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {

    @GetMapping("/map")
    public String modelMap(String name, ModelMap map) {	//modelmap作法
        map.addAttribute("name", name);
        map.addAttribute("from", "com.hung.springboot");
        // template名稱，實際目錄為：src/main/resources/templates/thymeleaf.html
        return "thymeleaf";
    }

    @GetMapping("/mv")
    public ModelAndView modelAndView(String name) {	//modelAndView作法
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", name);
        mv.addObject("from", "com.hung.springboot");
        // template名稱，實際目錄為：src/main/resources/templates/thymeleaf.html
        mv.setViewName("thymeleaf");
        return mv;
    }

}