package com.hung.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hung.model.Employee;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class EmployeeController {
	
    @GetMapping("/employee")
    public String home(Model model, HttpServletRequest request, HttpSession session) {
    	
        Employee emp1 = new Employee(1, "小名", 26, 5000.00f, LocalDate.of(2021, 4, 20), Arrays.asList("Java", "C++"));
        Employee emp2 = new Employee(2, "小華", 23, 4000.00f, LocalDate.of(2021, 5, 5), Arrays.asList("JavaScript", "Vue"));
        Employee emp3 = new Employee(3, "小李", 30, 8000.00f, LocalDate.of(2021, 6, 1), Arrays.asList("架構設計", "Java"));
        
        List<Employee> empList = new ArrayList<>();
        empList.add(emp1);
        empList.add(emp2);
        empList.add(emp3);
        
        model.addAttribute("userName", "XHC");
        model.addAttribute("message", "Spring Boot好簡單!");
        model.addAttribute("empList", empList);

        request.setAttribute("foo", "requestAttr");
        session.setAttribute("user", emp1);
        request.getServletContext().setAttribute("foo", "applicationAttr");

        // render 到 templates 中的 home.html
        return "home";
    }

}
