package com.whu.dao;

import com.whu.entity.Employee;
import com.whu.ybatis.annotation.*;
import com.whu.ybatis.pojo.SimpleDaoPage;

import java.util.List;
import java.util.Map;

/**
 * Created by hulichao on 2017/12/20
 */
//@Repository
@YDao
public interface EmployeeDao {

    /**
     * 查询返回Java对象
     * @param id
     * @return
     */
    @Sql("select * from employee where id = :id")
    Employee get(@Param("id") String id);

    /**
     * 修改数据
     * @param employee
     * @return
     */
    int update(@Param("employee") Employee employee);

    /**
     * 插入数据
     * @param employee
     */
    void insert(@Param("employee") Employee employee);

    /**
     * 通用分页方法，支持（oracle、mysql、SqlServer、postgresql）
     * @param employee
     * @param page
     * @param rows
     * @return
     */
    @ResultType(Employee.class)
    public SimpleDaoPage<Employee> getAll(@Param("employee") Employee employee, @Param("page")  int page, @Param("rows") int rows);

    /**
     * 删除数据
     * @param id
     */
    @Sql("delete from employee where id = :id")
    public void delete(@Param("id") String id);

    /**
     * 返回List<Map>类型，全部数据
     * @param employee
     * @return
     */
    @Arguments({ "employee"})
    @Sql("select * from employee")
    List<Map<String,Object>> getAll(Employee employee);

    /**
     * 支持多个参数，查看返回Map
     * @param empno
     * @param name
     * @return
     */
    @Sql("select * from employee where empno = :empno and  name = :name")
    Map<String,Object> getMap(@Param("empno") String empno,@Param("name")String name);

    /**
     * 查询分页
     * @return
     */
    @Sql("select count(*) from employee")
    Integer getCount();
}
