package com.shuishu.demo.rabbitmq.producer.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * @author ：谁书-ss
 * @date ：2022-04-04 10:22
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
@Configuration
public class RabbitMqTTLConfig {
    /**
     * 1、声明创建 交换机： ttl_direct_exchange
     * @return -
     */
    @Bean
    public DirectExchange directTTLExchange(){
        return new DirectExchange("ttl_direct_exchange");
    }

    /**
     * 2、声明创建 ttl队列： ttl.direct.queue
     * @return -
     */
    @Bean
    public Queue smsQueueDirectTTL(){
        Map<String, Object> args = new HashMap<>();
        // 设置 TTL过期时间，int类型 5秒
        args.put("x-message-ttl",5000);
        // 队列接收长度：3条消息
        args.put("x-max-length", 3);
        // 死信交换机
        args.put("x-dead-letter-exchange", "dead_direct_exchange");
        // 死信交换机 是路由direct  需要设置key
        args.put("x-dead-letter-routing-key", "dead");
        return new Queue("ttl.direct.queue",true, false, false, args);
    }

    /**
     * 测试 消息的TTL 队列
     * @return -
     */
    @Bean
    public Queue smsQueueDirectTTLMessage(){
        return new Queue("ttl.message.direct.queue",true);
    }


    /**
     * 3、交换机 和 队列绑定 指定路由key
     * @return -
     */
    @Bean
    public Binding smsBindingDirectTTL(){
        return BindingBuilder.bind(smsQueueDirectTTL()).to(directTTLExchange()).with("ttl");
    }

    @Bean
    public Binding smsBindingDirectTTLMessage(){
        return BindingBuilder.bind(smsQueueDirectTTLMessage()).to(directTTLExchange()).with("ttlMessage");
    }
}
