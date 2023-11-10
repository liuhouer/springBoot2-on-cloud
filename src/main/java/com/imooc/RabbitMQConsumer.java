package com.imooc;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component // 作为一个组件存在于springboot的容器中
public class RabbitMQConsumer {
    // watch观察者的意思
    @RabbitListener(queues = {RabbitMQConfig.QUEUE_MSG} ) // 指定所监听的队列,queues是 String List，说明可以监听多个序列
    public void watchQueue(String payload, Message message){
        // 监听队列会得到两个属性 payload 和 message

        // payload是消息的载体，其实就是消息内容
        System.out.println(payload);

        // 获取路由规则
        // 获得监听的队列的路由规则，如imooc.msg.send
        String routingKey = message.getMessageProperties().getReceivedRoutingKey();
        System.out.println(routingKey);
        if (routingKey.equalsIgnoreCase("imooc.msg.send")){
            // fixme:处理发送类业务
        } else if (routingKey.equalsIgnoreCase("imooc.msg.delete")){
            // fixme:处理删除类业务
        }
    }
}
