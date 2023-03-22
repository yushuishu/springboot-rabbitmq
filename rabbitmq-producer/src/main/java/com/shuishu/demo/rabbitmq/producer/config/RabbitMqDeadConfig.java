package com.shuishu.demo.rabbitmq.producer.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author ：谁书-ss
 * @date ：2022-04-04 11:23
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
@Configuration
public class RabbitMqDeadConfig {
    @Bean
    public DirectExchange deadDirectExchange(){
        return new DirectExchange("dead_direct_exchange");
    }

    @Bean
    public Queue deadDirectQueue(){
        return new Queue("dead.direct.queue",true);
    }

    @Bean
    public Binding deadDirectBinding(){
        return BindingBuilder.bind(deadDirectQueue()).to(deadDirectExchange()).with("dead");
    }

}
