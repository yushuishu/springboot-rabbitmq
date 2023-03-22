package com.shuishu.demo.rabbitmq.producer.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * @author ：谁书-ss
 * @date ：2022-05-03 22:11
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
@Configuration
public class RabbitMqConfirmConfig {
    @Bean
    public DirectExchange confirmExchange(){
        // ExchangeBuilder.directExchange("confirm.exchange").durable(true).withArgument("alternate-exchange", "backup.exchange").build();
        Map<String, Object> map = new HashMap<>(1);
        map.put("alternate-exchange", "backup.exchange");
        return new DirectExchange("confirm.exchange", true, false, map);
    }

    @Bean
    public Queue confirmQueue(){
        return new Queue("confirm.queue", true);
    }

    @Bean
    public Binding confirmBinding(){
        return BindingBuilder.bind(confirmQueue()).to(confirmExchange()).with("confirm_key");
    }


    /**
     *
     * =============================================== 备份配置
     *
     */

    @Bean
    public FanoutExchange backupExchange(){
        return new FanoutExchange("backup.exchange");
    }

    @Bean
    public Queue backupQueue(){
        return new Queue("backup.queue", true);
    }

    @Bean
    public Queue warningQueue(){
        return new Queue("warning.queue", true);
    }

    @Bean
    public Binding backupQueueBindingBackupExchange(){
        return BindingBuilder.bind(backupQueue()).to(backupExchange());
    }
    @Bean
    public Binding warningQueueBindingBackupExchange(){
        return BindingBuilder.bind(warningQueue()).to(backupExchange());
    }

}
