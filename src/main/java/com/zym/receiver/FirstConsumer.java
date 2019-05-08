package com.zym.receiver;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wangliang
 * @date 2019/5/7 0007
 */
@Component
public class FirstConsumer {

    @RabbitListener(queues = {"MyQueue1"})
    public void handleMessage(Message message){
        // 处理消息
        System.out.println("FirstConsumer {} handleMessage :"+message);
    }
}
