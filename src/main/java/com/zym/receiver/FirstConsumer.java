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

    /**
     * 监听MyQueue1的消息
     * @param message 接受到的消息
     */
    @RabbitListener(queues = {"MyQueue1"})
    public void handleMessage(Message message){
        // 处理消息
        System.out.println("FirstConsumer {} handleMessage :"+message);
    }

    /**
     * 监听MyFanoutQueue1的消息
     * @param message 接受到的消息
     */
    @RabbitListener(queues = {"FanoutQueue1"})
    public void handleMessageFanout1(Message message){
        // 处理消息
        System.out.println("FanoutQueue1 {} handleMessage1 :"+message);
    }

    /**
     * 监听MyFanoutQueue2的消息
     * @param message 接受到的消息
     */
    @RabbitListener(queues = {"FanoutQueue2"})
    public void handleMessageFanout2(Message message){
        // 处理消息
        System.out.println("FanoutQueue2 {} handleMessage2 :"+message);
    }

    /**
     * 监听MyFanoutQueue3的消息
     * @param message 接受到的消息
     */
    @RabbitListener(queues = {"FanoutQueue3"})
    public void handleMessageFanout3(Message message){
        // 处理消息
        System.out.println("FanoutQueue3 {} handleMessage3 :"+message);
    }

    /**
     * 监听msg.topic的消息
     * @param message 接受到的消息
     */
    @RabbitListener(queues = {"msg.topic"})
    public void handleMessageTopic(Message message){
        // 处理消息
        System.out.println("TopicQueue {} handleMessage :"+message);
    }
}
