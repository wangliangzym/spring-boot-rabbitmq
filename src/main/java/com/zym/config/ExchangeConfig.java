package com.zym.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangliang
 * @date 2019/5/7 0007
 */
@Configuration
public class ExchangeConfig {

    /**
     *   1.定义direct exchange，绑定queueTest
     *   2.durable="true" rabbitmq重启的时候不需要创建新的交换机
     *   3.direct交换器相对来说比较简单，匹配规则为：如果路由键匹配，消息就被投送到相关的队列
     *     fanout交换器中没有路由键的概念，他会把消息发送到所有绑定在此交换器上面的队列中。
     *     topic交换器你采用模糊匹配路由键的原则进行转发消息到队列中
     */
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(RabbitConfig.EXCHANGE,true,false);
    }

    /****************Topic交换机****************/
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(RabbitConfig.TOPICEXCHANGE,true,false);
    }

    /****************fanout交换机****************/
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(RabbitConfig.FANOUTEXCHANGE,true,false);
    }
}
