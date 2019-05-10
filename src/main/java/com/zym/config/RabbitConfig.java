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
    public static final String FANOUTEXCHANGE = "FanoutExchange";
    public static final String TOPICEXCHANGE = "TopicExchange";
    /**消息队列*/
    public static final String QUEUE1 = "MyQueue1";
    public static final String FANOUTQUEUE1 = "FanoutQueue1";
    public static final String FANOUTQUEUE2 = "FanoutQueue2";
    public static final String FANOUTQUEUE3 = "FanoutQueue3";
    public static final String TOPICQUEUE = "msg.topic";
    /**消息路由键*/
    public static final String ROUTINGKEY1 = "MyRoutingKey1";
    public static final String TOPICROUTINGKEY = "*.topic";


    /**
     * 将消息队列与交换机绑定
     */
    @Bean
    public Binding bing_one(){
       return BindingBuilder.bind(queueConfig.firstQueue()).to(exchangeConfig.directExchange()).with(ROUTINGKEY1);
    }

    /**
     * 将三个队列与fanout交换机绑定
     * @return
     */
    @Bean
    public Binding bingFanout1(){
        return BindingBuilder.bind(queueConfig.fanoutQueue1()).to(exchangeConfig.fanoutExchange());
    }

    @Bean
    public Binding bingFanout2(){
        return BindingBuilder.bind(queueConfig.fanoutQueue2()).to(exchangeConfig.fanoutExchange());
    }

    @Bean
    public Binding bingFanout3(){
        return BindingBuilder.bind(queueConfig.fanoutQueue3()).to(exchangeConfig.fanoutExchange());
    }

    @Bean
    public Binding bindingTopic(){
        return BindingBuilder.bind(queueConfig.topicQueue()).to(exchangeConfig.topicExchange()).with(TOPICROUTINGKEY);
    }

    /**
     * 自定义rabbit template用于数据的接收和发送
     * 可以设置消息确认机制和回调
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        /**若使用confirm-callback或return-callback，
         * 必须要配置publisherConfirms或publisherReturns为true
         * 每个rabbitTemplate只能有一个confirm-callback和return-callback
         */
        rabbitTemplate.setConfirmCallback(getMSCCallback());
        /**
         * 使用return-callback时必须设置mandatory为true，或者在配置中设置mandatory-expression的值为true，
         * 可针对每次请求的消息去确定’mandatory’的boolean值，
         * 只能在提供’return -callback’时使用，与mandatory互斥
         */
        rabbitTemplate.setReturnCallback(getMSRCallback());
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }

    /**
     关于 msgSendConfirmCallBack 和 msgSendReturnCallback 的回调说明：
     1.如果消息没有到exchange,则confirm回调,ack=false
     2.如果消息到达exchange,则confirm回调,ack=true
     3.exchange到queue成功,则不回调return
     4.exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
     */

    /**
     * 消息确认机制
     * Confirms给客户端一种轻量级的方式，能够跟踪哪些消息被broker处理，
     * 哪些可能因为broker宕掉或者网络失败的情况而重新发布。
     * 确认并且保证消息被送达，提供了两种方式：发布确认和事务。(两者不可同时使用)
     * 在channel为事务时，不可引入确认模式；同样channel为确认模式下，不可使用事务。
     * @return
     */
    public MsgSendConfirmCallback getMSCCallback(){
        return new MsgSendConfirmCallback();
    }

    public MsgSendReturnCallback getMSRCallback(){
        return new MsgSendReturnCallback();
    }

}
