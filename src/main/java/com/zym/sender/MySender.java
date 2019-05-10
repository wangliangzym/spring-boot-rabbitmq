package com.zym.sender;

import com.zym.config.RabbitConfig;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangliang
 * @date 2019/5/7 0007
 */
@Component
public class MySender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * direct消息发送
     * @param uuid
     * @param message
     */
    public void sendMsg(String uuid,Object message){
        CorrelationData correlationId = new CorrelationData(uuid);
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE,RabbitConfig.ROUTINGKEY1,message,correlationId);
    }

    /**
     * fanout消息发送
     * @param uuid
     * @param message
     */
    public void sendFanoutMsg(String uuid,Object message){
        CorrelationData correlationId = new CorrelationData(uuid);
        rabbitTemplate.convertAndSend(RabbitConfig.FANOUTEXCHANGE,"",message,correlationId);
    }

    /**
     * 发送topic消息
     * @param uuid
     * @param message
     */
    public void sendTopicMsg1(String uuid,Object message){
        CorrelationData correlationId = new CorrelationData(uuid);
        rabbitTemplate.convertAndSend(RabbitConfig.TOPICEXCHANGE,"abc.topic",message,correlationId);
    }
    public void sendTopicMsg2(String uuid,Object message){
        CorrelationData correlationId = new CorrelationData(uuid);
        rabbitTemplate.convertAndSend(RabbitConfig.TOPICEXCHANGE,"def.topic",message,correlationId);
    }

}
