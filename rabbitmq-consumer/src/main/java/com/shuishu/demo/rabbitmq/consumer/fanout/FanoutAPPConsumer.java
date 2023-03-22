package com.shuishu.demo.rabbitmq.consumer.fanout;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ：谁书-ss
 * @date ：2022-04-03 15:35
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
@Component
@RabbitListener(queues = {"app.fanout.queue"})
public class FanoutAPPConsumer {
    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("app fanout接收消息： " + message);
    }
}
