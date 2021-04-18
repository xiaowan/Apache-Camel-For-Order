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
 * 资产相关组件
 * @author licong
 * @date 2021/3/6 下午10:34
 */
@Component("useFundDiscountComponent")
public class UseFundDiscountComponent extends AbstractDiscountOperation implements IProcessor<OrderContext> {
    @Override
    public void execute(OrderContext orderContext, Exchange exchange) throws Exception {
        orderContext.getDiscountComponent().add("使用资产进行抵扣");

        List<ItemDetailDTO> itemDetailDTOList = this.getValidItems(orderContext.getMerchantItemDTOS());

        if (!CollectionUtils.isEmpty(itemDetailDTOList)) {
            itemDetailDTOList.stream()
                .filter(x -> ThreadLocalRandom.current().nextBoolean())
                .forEach(itemDetail -> {
                    itemDetail.addOrderResource(ResourceEnum.FUND, 7879890432890L, ThreadLocalRandom.current().nextInt(1, 5));
                });
        }
    }

}
