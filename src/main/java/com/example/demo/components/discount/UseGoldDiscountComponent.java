package com.example.demo.components.discount;

import com.example.demo.params.internal.ItemDetailDTO;
import com.example.demo.params.internal.ResourceEnum;
import com.example.demo.routes.dto.IProcessor;
import com.example.demo.routes.dto.OrderContext;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * 使用金币抵扣
 * @author licong
 * @date 2021/3/6 下午10:34
 */
@Component("useGoldDiscountComponent")
public class UseGoldDiscountComponent extends AbstractDiscountOperation implements IProcessor<OrderContext> {
    @Override
    public void execute(OrderContext orderContext, Exchange exchange) throws Exception {
        orderContext.getDiscountComponent().add("使用金币抵扣");

        List<ItemDetailDTO> itemDetailDTOList = this.getValidItems(orderContext.getMerchantItems());

        if (!CollectionUtils.isEmpty(itemDetailDTOList)) {
            itemDetailDTOList.stream()
                .filter(x -> ThreadLocalRandom.current().nextBoolean())
                .forEach(itemDetail -> {
                    itemDetail.addOrderResource(ResourceEnum.GOLD, null, ThreadLocalRandom.current().nextInt(1, 5));
                });
        }
    }

}
