package com.shuishu.demo.rabbitmq.consumer.direct;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ：谁书-ss
 * @date ：2022-04-03 15:33
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
@Component
@RabbitListener(queues = {"email.direct.queue"})
public class DirectEmailConsumer {
    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("email direct接收消息： " + message);
    }
}
