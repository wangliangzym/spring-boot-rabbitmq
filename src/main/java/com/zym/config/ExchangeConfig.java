package com.zym.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangliang
 * @date 2019/5/7 0007
 */
@Configuration
public class ExchangeConfig {

        @Bean
        public DirectExchange directExchange(){
            DirectExchange directExchange = new DirectExchange(RabbitConfig.EXCHANGE,true,false);
            return directExchange;
        }
}
