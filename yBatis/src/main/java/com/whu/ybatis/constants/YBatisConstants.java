package com.whu.ybatis.constants;

/**
 * Created by hulichao on 2017/12/22
 */
public class YBatisConstants {

    /**
     * 接口命名规则：添加：insert,add,create 添加：update,modify,store 刪除：delete,remove
     * 除上述之外即是查询
     */
    public static final String INF_METHOD_ACTIVE = "insert,add,create,update,modify,store,delete,remove";

    public static final String INF_METHOD_BATCH = "batch";
    /**
     * 方法有且只有一个参数 用户未使用@Arguments 标签 模板中引用参数默认为：tempDto
     */
    public static final String SQL_FTL_DTO = "tempDto";

}
