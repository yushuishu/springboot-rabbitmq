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
 * @date ：2022-05-03 15:53
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：绑定关系
 * <p></p>
 * 交换机 x
 * 死信交换机 y
 * TTL队列 q1
 * TTL队列 q2
 * 死信队列 qd
 */
@Configuration
public class RabbitMqDelayConfig {
    /**
     * 交换机： x_direct_exchange
     * @return -
     */
    @Bean
    public DirectExchange xDirectExchange(){
        return new DirectExchange("x_direct_exchange");
    }

    /**
     * 死信交换机： y_dead_direct_exchange
     * @return -
     */
    @Bean
    public DirectExchange yDirectExchange(){
        return new DirectExchange("y_dead_direct_exchange");
    }


    /**
     * ttl队列 q1： ttl.q1.direct.queue
     * @return -
     */
    @Bean
    public Queue q1TTLQueue(){
        Map<String, Object> args = new HashMap<>(3);
        // 设置 TTL过期时间，int类型 10秒
        args.put("x-message-ttl",10000);
        // 死信交换机
        args.put("x-dead-letter-exchange", "y_dead_direct_exchange");
        // 死信交换机 是路由direct  需要设置key
        args.put("x-dead-letter-routing-key", "YD");
        return new Queue("ttl.q1.direct.queue",true, false, false, args);
    }

    /**
     * ttl队列 q2： ttl.q2.direct.queue
     * @return -
     */
    @Bean
    public Queue q2TTLQueue(){
        Map<String, Object> args = new HashMap<>(3);
        // 设置 TTL过期时间，int类型 40秒
        args.put("x-message-ttl", 40000);
        // 死信交换机
        args.put("x-dead-letter-exchange", "y_dead_direct_exchange");
        // 死信交换机 是路由direct  需要设置key
        args.put("x-dead-letter-routing-key", "YD");
        return new Queue("ttl.q2.direct.queue",true, false, false, args);
    }

    /**
     * 死信队列 qd： qd.dead.direct.queue
     * @return -
     */
    @Bean
    public Queue qdQueue(){
        return new Queue("qd.dead.direct.queue",true, false, false, null);
    }


    @Bean
    public Binding q1BindingX(){
        return BindingBuilder.bind(q1TTLQueue()).to(xDirectExchange()).with("XQ1");
    }
    @Bean
    public Binding q2BindingX(){
        return BindingBuilder.bind(q2TTLQueue()).to(xDirectExchange()).with("XQ2");
    }
    @Bean
    public Binding qdBindingY(){
        return BindingBuilder.bind(qdQueue()).to(yDirectExchange()).with("YD");
    }
}
