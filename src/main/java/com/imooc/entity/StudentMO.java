package com.imooc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * MO:MongoDB Object
 */
@Document("students") // MongoDB的映射，文档(相当于表),如果文档students不存在，
// 会自动创建名为students的文档
@Data // 生成get、set
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StudentMO {
    @Id // 主键
    private String stuId;
    @Field("name") // 指定该字段的名称
    private String name;
    @Field("sex") // 指定该字段的名称
    private String sex;
    @Field("age") // 指定该字段的名称
    private Integer age;
}
