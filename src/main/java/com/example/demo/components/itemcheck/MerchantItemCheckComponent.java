package com.example.demo.components.itemcheck;

import com.example.demo.params.internal.MerchantItemDTO;
import com.example.demo.routes.dto.IProcessor;
import com.example.demo.routes.dto.OrderContext;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * 校验商品金额
 * @author licong
 * @date 2021/3/6 下午10:34
 */
@Component("merchantItemCheckComponent")
public class MerchantItemCheckComponent implements IProcessor<OrderContext> {
    @Override
    public void execute(OrderContext orderContext, Exchange exchange) throws Exception {
        List<MerchantItemDTO> merchantItemDTOS = orderContext.getMerchantItems();
        for (MerchantItemDTO merchantItemDTO : merchantItemDTOS) {
            if (ThreadLocalRandom.current().nextBoolean()) {
                merchantItemDTO.setInvalid("无效商家原因");
            }
         }
        orderContext.getItemChecks().add("商家相关检查");
    }

}
