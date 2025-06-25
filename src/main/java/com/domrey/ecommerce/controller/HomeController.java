package com.domrey.ecommerce.controller;
import com.domrey.ecommerce.entity.MyUser;
import com.domrey.ecommerce.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Profile("dev")
public class HomeController {

    @Autowired
    MyUserRepository myUserRepository;

    @RequestMapping(value = {"/", "/home"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String home() {
        return "home";
    }


    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/user")
    public String user(Model model) {
       List<MyUser> myUser = myUserRepository.findAll();
       model.addAttribute("users", myUser);
        return "user";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


}
