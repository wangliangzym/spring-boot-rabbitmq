package com.zym.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangliang
 * @date 2019/5/7 0007
 */
@Configuration
public class QueueConfig {

    /**
     * durable="true" 持久化 rabbitmq重启的时候不需要创建新的队列
     * auto-delete 表示消息队列没有在使用时将被自动删除 默认是false
     * exclusive  表示该消息队列是否只在当前connection生效,默认是false
     */
    @Bean
    public Queue firstQueue(){
        return new Queue(RabbitConfig.QUEUE1,true,false,false);
    }

    @Bean
    public Queue fanoutQueue1(){
        return new Queue(RabbitConfig.FANOUTQUEUE1,true,false,false);
    }

    @Bean
    public Queue fanoutQueue2(){
        return new Queue(RabbitConfig.FANOUTQUEUE2,true,false,false);
    }

    @Bean
    public Queue fanoutQueue3(){
        return new Queue(RabbitConfig.FANOUTQUEUE3,true,false,false);
    }

    @Bean
    public Queue topicQueue(){
        return new Queue("msg.topic",true,false,false);
    }
}
