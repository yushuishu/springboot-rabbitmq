package com.shuishu.demo.rabbitmq.consumer.topic;


import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author ：谁书-ss
 * @date ：2022-04-03 15:30
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "sms.topic.queue",durable = "true", autoDelete = "false"),
        exchange = @Exchange(value = "topic_order_exchange", type = ExchangeTypes.TOPIC),
        key = "#.sms.#"
))
public class TopicSMSConsumer {

    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("sms topic接收消息： " + message);
    }
}
