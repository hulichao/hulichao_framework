package com.whu.oliver.service;

import com.whu.oliver.form.LoginForm;

import java.util.List;
import java.util.Map;

/**
 * Created by hulichao on 2017/12/9
 */
public interface LoginService {

    /**
     * 增加
     * @param map
     * @return
     */
    int add(Map map);

    /**
     * 删除
     * @param map
     * @return
     */
    int delete(Map map);

    /**
     * 修改
     * @param map
     * @return
     */
    int update(Map map);

    /**
     * 查看
     * @param map
     * @return
     */
    int query(Map map);

     /** 登录
     * @param loginForm
     * @return
     */
    int login(LoginForm loginForm);

    List regist(LoginForm loginForm);
}
