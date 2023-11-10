package com.imooc;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 配置类
public class RabbitMQConfig {
    /**
     * 1.定义交换机的名称
     * 2.定义队列的名称
     * 3.创建交换机
     * 4.创建队列
     * 5.绑定队列和交换机（把队列绑定到交换机）
     */

    // 1.定义交换机的名称
    public static final String EXCHANGE_MSG="exchange_msg";

    // 2.定义队列的名称
    public static final String QUEUE_MSG="queue_msg";

    // 3.创建交换机
    @Bean(EXCHANGE_MSG) // 将其以Bean的形式保存在spring容器中
    public Exchange exchange(){
        // ExchangeBuilder构建一个交换机
        // topic是一种交换机了类型
        return ExchangeBuilder
                .topicExchange(EXCHANGE_MSG)
                .durable(true)
                .build();
        // durable:持久化，服务器重启后，MQ也会重启，
        // 重启以后，一些数据（如交换机）会丢失，因此这里可以进行持久化保存
    }

    // 4.创建队列
    @Bean(QUEUE_MSG) // 将其以Bean的形式保存在spring容器中
    public Queue queue(){
        return new Queue(QUEUE_MSG);
    }

    // 5.绑定队列和交换机（把队列绑定到交换机）

    // @Qualifier(QUEUE_MSG)： 在有多个同类Bean时，指定使用哪个Bean，这里指定名为QUEUE_MSG的Bean
    @Bean // 这里只绑定一个，因此不用命名，如果多个绑定就需要
    public Binding binding(
            @Qualifier(QUEUE_MSG) Queue queue,
            @Qualifier(EXCHANGE_MSG) Exchange exchange
    ){
        // 将queue绑定到exchange
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("imooc.msg.*") // 路由规则，相当于是controller里的路由
                .noargs(); // 不需要其他任何参数
        // "imooc.msg.*":路由规则，*代表泛型的，可以让更多的规则匹配到，
        // 如：imooc.msg.a,imooc.msg.b,imooc.msg.c 均是匹配到imooc.msg.*
    }
}
