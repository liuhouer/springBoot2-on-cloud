package com.imooc.controller;

import com.imooc.RabbitMQConfig;
import com.imooc.dao.response.FileUploadResponse;
import com.imooc.utils.MinioUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@Slf4j
@Controller // 不能使用Rest，不然不会返回页面
public class FileController {

    // 测试项目能否正常运行
    @GetMapping("fileUpload")
    public String FileUpload(){
        // 这里return的fileUpload是HTML的名称，是识别到在yml配置文件中
        // spring节点的thymeleaf中定义的路径以及文件后缀名
        return "fileUpload";
    }
    @Autowired
    private MinioUtil minioUtil;

    /**
     * 上传文件
     */
    @PostMapping("/upload")
    public FileUploadResponse upload(
            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam(required = false) String bucketName) {
        FileUploadResponse response = null;
        if (StringUtils.isBlank(bucketName)) {
            bucketName = "salt";
        }
        try {
            response = minioUtil.uploadFile(file, bucketName);
        } catch (Exception e) {
            log.error("上传失败 : [{}]", Arrays.asList(e.getStackTrace()));
        }
        return response;
    }
}
