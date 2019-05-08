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

    @Bean
    public Queue firstQueue(){
        Queue queue = new Queue(RabbitConfig.QUEUE1,true,false,false);
        return queue;
    }
}
