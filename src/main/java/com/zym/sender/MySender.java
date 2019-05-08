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
     * 消息发送
     * @param uuid
     * @param message
     */
    public void sendMsg(String uuid,Object message){
        CorrelationData correlationId = new CorrelationData(uuid);
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE,RabbitConfig.ROUTINGKEY1,message,correlationId);
    }
}
