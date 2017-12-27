package com.whu.test;

import com.whu.dao.EmployeeDao;
import com.whu.entity.Employee;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by hulichao on 2017/12/21
 */
public class ClientTest {
    public static void main(String args[]) {
        BeanFactory factory = new ClassPathXmlApplicationContext("applicationContext.xml");
        EmployeeDao employeeDao = (EmployeeDao) factory.getBean("employeeDao");
        Employee employee = new Employee();
        String id = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        employee.setId(id);
        employee.setEmpno("A001");
        employee.setSalary(new BigDecimal(5000));
        employee.setName("aaa");
        employee.setAge(25);
        //调用dao方法
        employeeDao.insert(employee);
    }
}
