package com.imooc.controller;

import com.imooc.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope // 当配置文件中的属性更改时，这里也属性值跟随更变
public class HelloController {

    // 测试项目能否正常运行
    @GetMapping("hello")
    public String hello(){
        return "hello mhq";
    }

    @Autowired // 注入一个模板
    private RabbitTemplate rabbitTemplate;

    // 创建生产者配置路由规则
    @GetMapping("producer")
    public String producer(){
        /**
         * RabbitMQ的路由规则： routing Key
         * imooc.msg.*.* -> 1个*代表一个占位符
         *  如：
         *      imooc.msg.do.display      匹配
         *      imooc.msg.display.send    匹配
         *      imooc.msg.sen.and.update  不匹配
         *
         *  imooc.msg.#  -> #代表多个占位符
         *  如：
         *      imooc.msg.a.b
         *      imooc.msg.a.b.c.d
         *      imooc.msg.a.aa.aaa
         */

        //参数： 1.交换机的名字，2.路由规则（要与“把队列绑定到交换机”时定义的规则一致），
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_MSG,
                "imooc.msg.send",
                "我发了一个消息");
        // 通过路由规则 "imooc.msg.send" 可以发送给我们交换机，交换机可以监听队列进行相应的处理
        // 不同的路由规则，可以让不同的监听的队列做不同的处理，send可以改为delete、update等

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_MSG,
                "imooc.msg.delete",
                "我删了一个消息");
        return "ok";
    }

    // 获取Nacos配置中心的属性
    @Value("${alibaba.config.limit}")
    private Integer limit;
    @Value("${alibaba.config.name}")
    private String name;

    @GetMapping("getConfig")
    public String getConfig(){
        return name+":"+limit;
    }

}
