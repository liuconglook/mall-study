package com.belean.mall.tiny.component;

import com.belean.mall.tiny.service.OmsPortalOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 取消订单消息的处理者
 */
@Component
@RabbitListener(queues = "mall.order.cancel")
@Slf4j
public class CancelOrderReceiver {

    @Autowired
    private OmsPortalOrderService portalOrderService;
    @RabbitHandler
    public void handle(Long orderId){
        log.info("receive delay message orderId:{}",orderId);
        portalOrderService.cancelOrder(orderId);
    }
}