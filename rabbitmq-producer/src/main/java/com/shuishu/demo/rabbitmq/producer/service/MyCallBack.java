package com.shuishu.demo.rabbitmq.producer.service;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


/**
 * @author ：谁书-ss
 * @date ：2022-05-03 22:18
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 * 交换机确认配置
 * spring.rabbitmq.publisher-confirms=true  低版本
 * spring.rabbitmq.publisher-confirm-type=correlated 高版本
 * <p></p>
 * 回退配置
 * publisher-returns: true
 * <p></p>
 * 每个RabbitTemplate只支持一个ConfirmCallback 、ReturnsCallback
 */
@Component
public class MyCallBack implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {
    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * rabbitTemplate 设置它的回调对象
     */
    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }

    /**
     * 交换机不管是否接收到消息，都会进行回调
     * @param correlationData - 消息的相关信息 (发送消息时，传递的信息)
     * @param b               - ack    是否确认收到 (true:收到; false:未收到)
     * @param s               - cause  没有收到消息的原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        String id = correlationData != null ? correlationData.getId() : "";
        if (b){
            System.out.println("交换机已收到 id 为： "+ id);
        }else {
            System.out.println("交换机未收到 id 为： "+ id + "  原因： " + s);
        }
    }


    /**
     * 当消息不可达时(备份交换机也没有)才进行回退
     * @param returnedMessage -
     */
    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        System.out.println("消息: " + new String(returnedMessage.getMessage().getBody()));
        System.out.println("交换机: " + returnedMessage.getExchange());
        System.out.println("回退原因: " + returnedMessage.getReplyText());
        System.out.println("路由key: " + returnedMessage.getRoutingKey());
        System.out.println("code: " + returnedMessage.getReplyCode());
    }
}
