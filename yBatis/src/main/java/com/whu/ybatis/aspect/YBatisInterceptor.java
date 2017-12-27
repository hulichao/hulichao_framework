package com.whu.ybatis.aspect;

import java.lang.reflect.Field;

/**
 * Created by hulichao on 2017/12/21
 */
public interface YBatisInterceptor {
    /**
     * 插入拦截
     * @param fields
     * @return
     */
    public boolean onInsert(Field[] fields, Object obj);
    /**
     * 修改拦截
     * @param fields
     * @return
     */
    public boolean onUpdate(Field[] fields,Object obj);
}
