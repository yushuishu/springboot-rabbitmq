package com.shuishu.demo.rabbitmq.consumer.delay;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author ：谁书-ss
 * @date ：2022-05-03 16:55
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
@Component
@RabbitListener(queues = {"qd.dead.direct.queue"})
public class DeadLetterQueueConsumer {
    @RabbitHandler
    public void receiveD(String message){
        System.out.println("接收时间： " + new Date() + " 接收消息： " + message);
    }
}
