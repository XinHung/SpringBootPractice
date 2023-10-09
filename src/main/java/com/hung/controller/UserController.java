package com.hung.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hung.model.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("")
	public String demo(@RequestParam(name="userName", defaultValue="Hung") String name,
						@RequestParam(name="password", required=false) String password) {
		System.out.println(name);
		if(password != null)
			System.out.println(password);
		return "finish!";
	}
	
	@GetMapping("/register")
	public String register(User user, Model model, HttpServletRequest request) {
		String userIp = request.getRemoteAddr();
		model.addAttribute("registerIp", userIp);
		return "register";
	}
	
	@PostMapping("/register")
	public String register(@Valid User user, BindingResult result) {
		if(result.hasErrors()) {
			return "register";
		}
		Date now = new Date();
		user.setRegDate(now);
		return "success";
	}
	
	@GetMapping("/login")
	public String doLogin() {
		return "login";
	}
	
	@PostMapping("/login")
    public String login(HttpServletRequest request, HttpSession session, Model model) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        if("admin".equals(userName) && "1234".equals(password)) {
            // 驗證成功，存入 session
            session.setAttribute("user", userName);
            // 取得原始 URI
            String originUri = request.getParameter("originUri");
            // 如果不為空，導向該頁面
            if(null != originUri && !originUri.isEmpty()){
                return "redirect:" + originUri;
            } else {
                return "redirect:/index";
            }
        } else {
            // 驗證失敗，取得原始 URI
            // 如果 URI 存在，再次將它做為 originUri
            String originUri = request.getParameter("originUri");
            if(null != originUri && !originUri.isEmpty()) {
                request.setAttribute("originUri", originUri);
            }
            model.addAttribute("error", "帳號或密碼錯誤！");
            return "login";
        }
    }
}
