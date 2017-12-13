package com.whu;

import java.util.List;
import java.util.Map;

/**
 * Created by hulichao on 2017/12/11
 */
public class TestDogDb {
    private static final String uuid  = "03c9d61b-2cef-4f73-a01d-023a11ea32f2";
    private static final String database_name  = "dog/dog.xml";

    public static void main(String[] args) {
//        new TestDogDb().createDatabse();//创建数据库
//        new TestDogDb().insertData();//插入数据
//        new TestDogDb().updateData();//修改数据
        new TestDogDb().deleteData();//删除数据
        new TestDogDb().listAll();//列表数据
    }


    /**
     * 创建数据库
     */
    public void createDatabse(){
        try {
            DogDb dao = new DogDb();
            dao.createDataBase(database_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入数据
     */
    public void insertData(){
        Person po = new Person();
        po.setName("lisan");
        po.setAge(20);
        po.setMoney(2000.98);
        po.setSex("男");
        try {
            DogDb dao = new DogDb();
            dao.addData(database_name,"cat",po);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改数据
     */
    public void updateData(){
        Person po = new Person();
        po.set_uuid(uuid);
        po.setName("lallal");
        po.setAge(28);
        try {
            DogDb dao = new DogDb();
            dao.updateData(database_name,"cat",po);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 删除数据
     */
    public void deleteData(){
        Person po = new Person();
        po.set_uuid(uuid);
        try {
            DogDb dao = new DogDb();
            dao.deleteData(database_name,"cat",po);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有数据
     */
    public void listAll(){
        try {
            DogDb dao = new DogDb();
            List<Map> list = dao.loadTableDatas(database_name,"cat");
            System.out.println(list.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
