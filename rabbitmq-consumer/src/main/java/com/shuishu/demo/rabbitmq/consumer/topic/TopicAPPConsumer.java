package com.shuishu.demo.rabbitmq.consumer.topic;


import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
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
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "app.topic.queue",durable = "true", autoDelete = "false"),
        exchange = @Exchange(value = "topic_order_exchange", type = ExchangeTypes.TOPIC),
        key = "com.#"
))
public class TopicAPPConsumer {
    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println("app topic接收消息： " + message);
    }
}
