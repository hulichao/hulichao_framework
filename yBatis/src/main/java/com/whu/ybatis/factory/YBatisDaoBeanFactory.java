package com.whu.ybatis.factory;

import com.whu.ybatis.aop.YBatisHandler;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

/**
 * Created by hulichao on 2017/12/21
 */
public class YBatisDaoBeanFactory<T> implements FactoryBean<T> {
    private Class<T> daoInterface;

    private YBatisHandler proxy;

    public T getObject() throws Exception {
        return newInstance();
    }

    public Class<?> getObjectType() {
        return daoInterface;
    }

    public YBatisHandler getProxy() {
        return proxy;
    }

    public boolean isSingleton() {
        return true;
    }

    @SuppressWarnings("unchecked")
    private T newInstance() {
        return (T) Proxy.newProxyInstance(daoInterface.getClassLoader(), new Class[] { daoInterface }, proxy);
    }

    public void setProxy(YBatisHandler proxy) {
        this.proxy = proxy;
    }

    public void setDaoInterface(Class<T> daoInterface) {
        this.daoInterface = daoInterface;
    }
}
