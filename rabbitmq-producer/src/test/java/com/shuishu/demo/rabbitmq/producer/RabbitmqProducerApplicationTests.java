package com.shuishu.demo.rabbitmq.producer;

import com.shuishu.demo.rabbitmq.producer.service.OrderService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqProducerApplicationTests {
    @Resource
    private OrderService orderService;

    @Test
    void contextLoads() {
        //orderService.saveOrderFanout();
        orderService.saveOrderDirect();
        //orderService.saveOrderTopic();
        //orderService.saveOrderDirectTTL();
        //orderService.saveOrderDirectTTLMessage();
        //for (int i = 0; i < 10; i++) {
        //    orderService.saveOrderDirectTTL();
        //}
        //orderService.saveOrderDirectDelay();
        //orderService.saveOrderConfirmCallback();
    }

}
