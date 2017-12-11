package com.whu.oliver.controller;

import com.whu.oliver.annotation.*;
import com.whu.oliver.form.LoginForm;
import com.whu.oliver.service.LoginService;
import java.io.Serializable;
import java.util.List;

/**
 * Created by hulichao on 2017/12/9
 */

@Controller("loginController")
public class LoginController implements Serializable{
    @Quatifier("loginService")
    LoginService loginService;

    @RequestMapping("login")
    public String login(@Param("name") String name, @Param("password") String password) {
        int result = loginService.login(new LoginForm(name,password));
        if (result == 0)
            return "redirect:/view/success.jsp";
        else{//失败
            return "/view/error.jsp";
        }
    }

    @JSON
    @RequestMapping("regist")
    public List regist(@Param("name") String name, @Param("password") String password) {
        List result = loginService.regist(new LoginForm(name,password));
        return result;
    }
}
