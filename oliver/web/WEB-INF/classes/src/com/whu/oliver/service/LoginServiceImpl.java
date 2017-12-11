package com.whu.oliver.service;

import com.whu.oliver.annotation.Service;
import com.whu.oliver.form.LoginForm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hulichao on 2017/12/9
 */

@Service("loginService")
public class LoginServiceImpl implements LoginService {
    @Override
    public int add(Map map) {
        System.out.println("add");
        return 0;
    }

    @Override
    public int delete(Map map) {
        System.out.println("delete");
        return 0;
    }

    @Override
    public int update(Map map) {
        System.out.println("update");
        return 0;
    }

    @Override
    public int query(Map map) {
        System.out.println("query");
        return 0;
    }

    @Override
    public int login(LoginForm loginForm) {
        if("admin".equals(loginForm.getName()))
        return 0;
        else return 1;
    }

    @Override
    public List regist(LoginForm loginForm) {
        List list = new ArrayList();
        for (int i=0;i<10;i++){
            list.add(loginForm.toString()+":"+i);
        }
        return list;
    }
}
