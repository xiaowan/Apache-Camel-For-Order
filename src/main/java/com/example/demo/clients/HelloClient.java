package com.example.demo.clients;

import com.example.demo.input.pre.PreVirtualOrder;
import org.apache.camel.Produce;

import java.util.List;

/**
 * @author licong
 * @date 2021/2/4 下午7:43
 */
public interface HelloClient {

    List<String> sayHello(List<String> content);
}
