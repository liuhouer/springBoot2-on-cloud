package com.imooc.controller;


import com.imooc.dao.StuDao;
import com.imooc.entity.StudentMO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("mongo")
public class MongoController {
    @Autowired
    private StuDao stuDao;

    @GetMapping("create")
    public String create(){
        StudentMO stu = new StudentMO("1001","Jack","男",18);
        stuDao.save(stu);
        return "ok";
    }

    @GetMapping("get")
    public Object get(String sid){
        Optional<StudentMO> op = stuDao.findById(sid); // findById 返回的是一个Optional<T>,T为该数据的类型
        return op.get();
    }

    // 更新和插入用的同一个
    @GetMapping("update")
    public String update(){
        StudentMO stu = new StudentMO("1001","CuiHua","女",22);
        stuDao.save(stu);
        return "ok";
    }

    @GetMapping("delete")
    public String delete(String sid){
        stuDao.deleteById(sid);
        return "ok";
    }
}
