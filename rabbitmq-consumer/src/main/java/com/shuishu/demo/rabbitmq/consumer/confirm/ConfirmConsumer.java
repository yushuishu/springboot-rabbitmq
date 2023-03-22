package com.shuishu.demo.rabbitmq.consumer.confirm;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ：谁书-ss
 * @date ：2022-05-03 22:42
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
@Component
@RabbitListener(queues = {"confirm.queue"})
public class ConfirmConsumer {
    @RabbitHandler
    public void reviceConfirmMessage(String message){
        System.out.println("消费者 接收消息： " + message);
    }
}
