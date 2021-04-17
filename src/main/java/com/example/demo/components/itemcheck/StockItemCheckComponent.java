package com.example.demo.components.itemcheck;

import com.example.demo.params.internal.ItemDetailDTO;
import com.example.demo.params.internal.MerchantItemDTO;
import com.example.demo.routes.dto.IProcessor;
import com.example.demo.routes.dto.OrderContext;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.camel.Exchange;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * 校验商品金额
 * @author licong
 * @date 2021/3/6 下午10:34
 */
@Component("stockItemCheckComponent")
public class StockItemCheckComponent implements IProcessor<OrderContext> {
    @Override
    public void execute(OrderContext orderContext, Exchange exchange) throws Exception {
        List<ItemDetailDTO> itemDetailDTOList = new ArrayList<>();
        for (MerchantItemDTO merchantItemDTO : orderContext.getMerchantItemDTOS()) {
            itemDetailDTOList.addAll(merchantItemDTO.getItemDetailDTOList());
        }
        for (ItemDetailDTO itemDetailDTO : itemDetailDTOList) {
            if (itemDetailDTO.getInvalid()) {
                continue;
            }
            itemDetailDTO.setStock(ThreadLocalRandom.current().nextInt(5, 10));
        }
        orderContext.getItemChecks().add("库存相关检查");
    }

}
