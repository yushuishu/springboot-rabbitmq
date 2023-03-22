package com.shuishu.demo.rabbitmq.producer.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author ：谁书-ss
 * @date ：2022-04-03 15:45
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 * 1、创建 交换机： direct
 * 2、创建队列： sms.direct.queue    email.direct.queue    app.direct.queue
 * 3、绑定关系，指定路由key：sms   email   app
 */
@Configuration
public class RabbitMqDirectConfig {
    /**
     * 1、声明创建 交换机： direct
     * @return -
     */
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("direct_order_exchange");
    }

    /**
     * 2、声明创建 3个队列： sms.direct.queue    email.direct.queue    app.direct.queue
     * @return -
     */
    @Bean
    public Queue smsQueueDirect(){
        return new Queue("sms.direct.queue",true);
    }
    @Bean
    public Queue emailQueueDirect(){
        return new Queue("email.direct.queue",true);
    }
    @Bean
    public Queue appQueueDirect(){
        return new Queue("app.direct.queue",true);
    }

    /**
     * 3、交换机 和 队列绑定 指定路由key
     * @return -
     */
    @Bean
    public Binding smsBindingDirect(){
        return BindingBuilder.bind(smsQueueDirect()).to(directExchange()).with("sms");
    }
    @Bean
    public Binding emailBindingDirect(){
        return BindingBuilder.bind(emailQueueDirect()).to(directExchange()).with("email");
    }
    @Bean
    public Binding appBindingDirect(){
        return BindingBuilder.bind(appQueueDirect()).to(directExchange()).with("app");
    }

}
