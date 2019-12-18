package com.foreverything.bbs.controller;


import com.foreverything.bbs.entities.User;
import com.foreverything.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.io.PrintWriter;
import java.util.List;


/**
 * @ClassName UserController
 * @Author LiuJingxin
 * @Date Created in 15:10 2019/12/17
 * @Description
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    HttpSession session;

    @PostMapping("/login")
    public String userLogin(@RequestParam("username") String username,@RequestParam("password") String password){
        if(userService.getID(Integer.parseInt(username))){
            if(password.equals(userService.getPas(Integer.parseInt(username)))) {
                session.setAttribute("name", username);
                return "index";
            }
            else {

                return "login";
            }
        }
        else
            return "login";
    }
    @PostMapping("/register")
    public String createUser(User user,@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("mail") String mail){
        /*user.setAccount(username);
        user.setPassword(password);
        user.setMail(mail);*/
        userService.insertUser(username,password,mail);
        return "login";
    }
}
