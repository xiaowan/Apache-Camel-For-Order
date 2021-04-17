package com.example.demo.config.biz;

import com.example.demo.config.biz.mq.Mq;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * 订单相关配置
 */
@Data
@ConfigurationProperties(prefix = "order")
public class GlobalConfiguration {

    /**订单配置*/
    private Map<String, OrderConfiguration> orderConfiguration;

    /**es服务器地址*/
    private String esUrl;

    /**止血开关*/
    private Stanch stanch;

    /**MQ 配置*/
    private Mq mq;


}
