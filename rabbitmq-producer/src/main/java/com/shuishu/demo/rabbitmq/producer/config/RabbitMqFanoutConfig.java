package com.shuishu.demo.rabbitmq.producer.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author ：谁书-ss
 * @date ：2022-04-03 15:18
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 * 1、创建 交换机： fanout
 * 2、创建队列： sms.fanout.queue    email.fanout.queue    app.fanout.queue
 * 3、绑定关系
 */
@Configuration
public class RabbitMqFanoutConfig {

    /**
     * 1、声明创建 交换机： fanout
     * @return -
     */
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanout_order_exchange");
    }

    /**
     * 2、声明创建 3个队列： sms.fanout.queue    email.fanout.queue    app.fanout.queue
     * @return -
     */
    @Bean
    public Queue smsQueue(){
        return new Queue("sms.fanout.queue",true);
    }
    @Bean
    public Queue emailQueue(){
        return new Queue("email.fanout.queue",true);
    }
    @Bean
    public Queue appQueue(){
        return new Queue("app.fanout.queue",true);
    }

    /**
     * 3、交换机 和 队列绑定
     * @return -
     */
    @Bean
    public Binding smsBinding(){
        return BindingBuilder.bind(smsQueue()).to(fanoutExchange());
    }
    @Bean
    public Binding emailBinding(){
        return BindingBuilder.bind(emailQueue()).to(fanoutExchange());
    }
    @Bean
    public Binding appBinding(){
        return BindingBuilder.bind(appQueue()).to(fanoutExchange());
    }

}
