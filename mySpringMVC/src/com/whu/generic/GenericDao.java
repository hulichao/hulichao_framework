package com.whu.generic;

import java.util.Set;

/**
 * Created by hulichao on 2017/12/6
 */
public class GenericDao<T> {

    public  void add(T x){}

    public  T findById(int id){return  null;}

    public void delete(T obj){};

    public void delete(int id){};

    public void update(T obj){};

    public Set<T> findByConditions(String where){
        return null;
    }

    public T findByUserName(String name){return null;}
}
