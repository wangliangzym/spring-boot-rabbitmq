package com.zym.mqcallback;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.lang.Nullable;

/**
 * @author wangliang
 * @date 2019/5/7 0007
 * 消息发送到交换机确认机制
 */
public class MsgSendConfirmCallback implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(@Nullable CorrelationData correlationData, boolean acs, @Nullable String cause) {
        System.out.println("MsgSendConfirmCallBack  , 回调id:" + correlationData);
        if(acs){
            System.out.println("消息消费成功");
        }else {
            System.out.println("消息消费失败");
        }
    }
}
