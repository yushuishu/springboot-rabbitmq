package com.shuishu.demo.rabbitmq.producer.config;


import org.springframework.amqp.core.CustomExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * @author ：谁书-ss
 * @date ：2022-05-03 20:10
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
@Configuration
public class RabbitMqDelayPluginConfig {
    /**
     * 自定义交换机
     * @return -
     */
    @Bean
    public CustomExchange delayPluginExchange(){
        /*
         * 1、交换机名称
         * 2、交换机类型
         * 3、是否需要持久化
         * 4、是否需要自动删除
         * 5、其他参数
         */
        Map<String, Object> map = new HashMap<>(1);
        map.put("x-delayed-type", "direct");
        return new CustomExchange("delay_plugin_exchange",
                "x-delayed-message",
                true,
                false,
                map);
    }

}
