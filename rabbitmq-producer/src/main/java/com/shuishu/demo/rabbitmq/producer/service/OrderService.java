package com.shuishu.demo.rabbitmq.producer.service;


import jakarta.annotation.Resource;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;


/**
 * @author ：谁书-ss
 * @date ：2022-04-03 15:11
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
@Service
public class OrderService {
    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * fanout 类型 测试
     */
    public void saveOrderFanout(){
        // 1、生成订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生成： " + orderId);
        // 2、保存订单。。。
        // 3、发送消息通知
        String exchangeName = "fanout_order_exchange";
        String routingKey = "";
        // 参数1：交换机    参数2：路由key/queue队列名   参数3：消息内容
        rabbitTemplate.convertAndSend(exchangeName, routingKey, orderId);
    }

    /**
     * direct 类型 测试
     */
    public void saveOrderDirect(){
        // 1、生成订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生成： " + orderId);
        // 2、保存订单。。。
        // 3、发送消息通知
        String exchangeName = "direct_order_exchange";
        rabbitTemplate.convertAndSend(exchangeName, "sms", orderId);
        rabbitTemplate.convertAndSend(exchangeName, "email", orderId);
    }

    /**
     * topic 类型 测试
     */
    public void saveOrderTopic(){
        // 1、生成订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生成： " + orderId);
        // 2、保存订单。。。
        // 3、发送消息通知
        String exchangeName = "topic_order_exchange";
        // #.sms.#
        // *.email.#
        // com.#
        // com.order.email.sms 测试结果  sms  app 接收消息，email规则前 * 只能匹配一级
        String routingKey = "com.order.email.sms";
        rabbitTemplate.convertAndSend(exchangeName, routingKey, orderId);
    }

    /**
     * TTL 类型 测试
     */
    public void saveOrderDirectTTL(){
        // 1、生成订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生成： " + orderId);
        // 2、保存订单。。。
        // 3、发送消息通知
        String exchangeName = "ttl_direct_exchange";
        rabbitTemplate.convertAndSend(exchangeName, "ttl", orderId);
    }

    /**
     * TTL 类型 测试
     */
    public void saveOrderDirectTTLMessage(){
        // 1、生成订单
        String orderId = UUID.randomUUID().toString();
        System.out.println("订单生成： " + orderId);
        // 2、保存订单。。。
        // 3、发送消息通知
        String exchangeName = "ttl_direct_exchange";

        // 4、给消息设置过期时间 TTL
        MessagePostProcessor messagePostProcessor = message -> {
            message.getMessageProperties().setExpiration("5000");
            message.getMessageProperties().setContentEncoding("UTF-8");
            return message;
        };

        rabbitTemplate.convertAndSend(exchangeName, "ttlMessage", orderId, messagePostProcessor);
    }


    /**
     * 延时队列 测试
     */
    public void saveOrderDirectDelay(){
        // 1、消息
        String orderId = UUID.randomUUID().toString();
        System.out.println("发送时间： "+ new Date() + " 发送消息： " + orderId);
        // 2、发送消息通知
        rabbitTemplate.convertAndSend("x_direct_exchange", "XQ1", "ttl为10s的队列： "+ orderId);
        rabbitTemplate.convertAndSend("x_direct_exchange", "XQ2", "ttl为40s的队列： "+ orderId);
    }

    public void saveOrderConfirmCallback(){
        // 1、消息
        String orderId = UUID.randomUUID().toString();
        System.out.println("发送消息： " + orderId);

        // 发送消息通知  id = 1
        CorrelationData correlationData1 = new CorrelationData("1");
        rabbitTemplate.convertAndSend("confirm.exchange", "confirm_key", orderId + " id=1", correlationData1);
        // 发送消息通知  id = 2
        CorrelationData correlationData2 = new CorrelationData("2");
        rabbitTemplate.convertAndSend("confirm.exchange", "confirm_key2", orderId + " id=2", correlationData2);
    }

    public void saveOrderMaxPriority(){
        // 1、消息
        String orderId = UUID.randomUUID().toString();
        System.out.println("发送消息： " + orderId);

        rabbitTemplate.convertAndSend("exchange", "key",
                orderId,
                message -> {
                    message.getMessageProperties().setPriority(5);
                    return message;
                }
                );

    }
}
