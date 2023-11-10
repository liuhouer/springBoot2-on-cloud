package com.imooc.dao;

import com.imooc.entity.StudentMO;
import org.springframework.data.mongodb.repository.MongoRepository;

// MongoRepository<T,ID> T为当前的数据类型，ID为主键

/**
 * 使用JPA的方式进行数据层的操作，继承MongoRepository后就包含了很多通用的方法，可以直接使用
 */
public interface StuDao extends MongoRepository<StudentMO,String> { }
