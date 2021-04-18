package com.example.demo.components.discount;

import com.example.demo.params.internal.ItemDetailDTO;
import com.example.demo.params.internal.ResourceEnum;
import com.example.demo.routes.dto.IProcessor;
import com.example.demo.routes.dto.OrderContext;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import java.util.List;
import org.springframework.util.CollectionUtils;

/**
 * 优惠券计算
 * @author licong
 * @date 2021/3/6 下午10:34
 */
@Component("useCouponComponent")
public class UseCouponComponent extends AbstractDiscountOperation implements IProcessor<OrderContext> {
    @Override
    public void execute(OrderContext orderContext, Exchange exchange) throws Exception {
        orderContext.getDiscountComponent().add("使用优惠券优惠");

        List<ItemDetailDTO> itemDetailDTOList = this.getValidItems(orderContext.getMerchantItemDTOS());

        if (!CollectionUtils.isEmpty(itemDetailDTOList)) {
            itemDetailDTOList.stream()
                .filter(x -> ThreadLocalRandom.current().nextBoolean())
                .forEach(itemDetail -> {
                    itemDetail.addOrderResource(ResourceEnum.COUPON, 23432890L, ThreadLocalRandom.current().nextInt(1, 5));
                });
        }

    }

}
