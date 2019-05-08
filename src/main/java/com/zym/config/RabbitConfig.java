package com.zym.config;

import com.zym.mqcallback.MsgSendConfirmCallback;
import com.zym.mqcallback.MsgSendReturnCallback;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangliang
 * @date 2019/5/7 0007
 */
@Configuration
public class RabbitConfig {

    @Autowired
    private QueueConfig queueConfig;
    @Autowired
    private ExchangeConfig exchangeConfig;
    @Autowired
    private ConnectionFactory connectionFactory;

    /**消息交换机*/
    public static final String EXCHANGE = "MyExchange";
    /**消息队列*/
    public static final String QUEUE1 = "MyQueue1";
    /**消息路由键*/
    public static final String ROUTINGKEY1 = "MyRoutingKey1";

    /**
     * 将消息队列与交换机绑定
     */
    @Bean
    public Binding bing_one(){
       return BindingBuilder.bind(queueConfig.firstQueue()).to(exchangeConfig.directExchange()).with(ROUTINGKEY1);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setConfirmCallback(getMSCCallback());
        rabbitTemplate.setReturnCallback(getMSRCallback());
        return rabbitTemplate;
    }

    public MsgSendConfirmCallback getMSCCallback(){
        return new MsgSendConfirmCallback();
    }

    public MsgSendReturnCallback getMSRCallback(){
        return new MsgSendReturnCallback();
    }

}
