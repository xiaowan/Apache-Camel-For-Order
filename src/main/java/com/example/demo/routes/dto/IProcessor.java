package com.example.demo.routes.dto;

import org.apache.camel.Exchange;

/**
 * @author licong
 * @date 2020/10/25 11:43 上午
 */
public interface IProcessor<T> {
    /**
     * 正向执行业务
     * @param t
     * @param exchange
     * @throws Exception
     */
     void execute(T t, Exchange exchange) throws Exception;

    /**
     * 业务异常回滚补偿
     * 默认空实现，如果不需要回滚则无需实现该方法
     * @param t
     * @param exchange
     */
    default void rollback(T t, Exchange exchange) {
    }
}
