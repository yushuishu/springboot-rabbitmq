package com.shuishu.demo.rabbitmq.consumer.confirm;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ：谁书-ss
 * @date ：2022-05-04 9:30
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
@Component
@RabbitListener(queues = {"warning.queue"})
public class WarningConsumer {
    @RabbitHandler
    public void reviceWarningMessage(String message){
        System.out.println("接收警告消息： " + message);
    }
}
