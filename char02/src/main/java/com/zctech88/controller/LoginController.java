package com.zctech88.controller;

import com.zctech88.domain.User;
import com.zctech88.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class LoginController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/index.html")
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "loginCheck.html")
    public ModelAndView loginCheck(HttpServletRequest request, LoginCommand
            loginCommand) {
        boolean isValidUser = userService.hasMatchUser(loginCommand
                .getUserName(), loginCommand.getPassword());

        if (!isValidUser) {
            return new ModelAndView("login", "error", "用户名或密码错误。");
        } else {
            User user = userService.findUserByUserName(loginCommand
                    .getUserName());
            user.setLastIp(request.getLocalAddr());
            user.setLastVisit(new Date());
            userService.loginSuccess(user);
            request.getSession().setAttribute("user", user);
            return new ModelAndView("main");
        }
    }

    /*@Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }*/
}
