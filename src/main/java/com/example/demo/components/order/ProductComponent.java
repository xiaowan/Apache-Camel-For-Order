package com.example.demo.components.order;

import com.example.demo.params.internal.InternalItemInfo;
import com.example.demo.params.internal.ItemDetailDTO;
import com.example.demo.params.internal.MerchantItemDTO;
import com.example.demo.routes.dto.IProcessor;
import com.example.demo.routes.dto.OrderContext;
import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 商品相关操作
 * @author licong
 * @date 2021/3/6 下午12:22
 */
@Component
public class ProductComponent implements IProcessor<OrderContext> {

    /**
     * 调用商家中心，组装商品详情
     */
    @Override
    @Handler
    public void execute(@Body OrderContext orderContext, Exchange exchange) throws Exception {
        List<InternalItemInfo> internalItemInfoList = orderContext.getInternalItemInfoList();
        if (CollectionUtils.isEmpty(internalItemInfoList)) {
            return;
        }

        List<ItemDetailDTO> itemDetailDTOS = new ArrayList<>();

        for ( int i = 0; i < 5; i ++ ) {
            ItemDetailDTO itemDetailDTO = this.buildItemDetailDTO(internalItemInfoList.get(0));
            itemDetailDTOS.add(itemDetailDTO);
            /**mock数据*/
            if (i == 0 || i == 1) {
                itemDetailDTO.setInvalid(true);
            }
        }

        orderContext.setItemDetailDTOS(itemDetailDTOS);
    }

    private ItemDetailDTO buildItemDetailDTO(InternalItemInfo internalItemInfo) {
        ItemDetailDTO itemDetailDTO = new ItemDetailDTO();
        itemDetailDTO.setItemCode(internalItemInfo.getItemCode());
        itemDetailDTO.setNum(internalItemInfo.getNum());
        itemDetailDTO.setOrigPrice(ThreadLocalRandom.current().nextInt(40,45));
        itemDetailDTO.setRealPrice(ThreadLocalRandom.current().nextInt(20, 25));
        itemDetailDTO.setThumbImg("https://www.baidu.com/img/PCpad_012830ebaa7e4379ce9a9ed1b71f7507.png");
        itemDetailDTO.setItemName(UUID.randomUUID().toString());
        itemDetailDTO.setActivityId(internalItemInfo.getActivityId());
        itemDetailDTO.setPriceId(internalItemInfo.getPriceId());
        if (ThreadLocalRandom.current().nextBoolean()) {
            itemDetailDTO.setMerchantId(24328549308694L);
            itemDetailDTO.setShopId(433859438540305L);
        } else {
            itemDetailDTO.setMerchantId(3211143928908L);
            itemDetailDTO.setShopId(643543985085940L);
        }
        itemDetailDTO.setInvalid(false);
        return itemDetailDTO;
    }


    /**
     * 校验是否存在无效商品类型
     * @param orderContext
     * @param exchange
     */
    public void checkInvalidItemDetail(@Body OrderContext orderContext, Exchange exchange) {
        List<MerchantItemDTO> merchantItemDTOS = orderContext.getMerchantItemDTOS();
        if (CollectionUtils.isEmpty(merchantItemDTOS)) {
            return;
        }

        List<ItemDetailDTO> invalidItemDetailDTO = new ArrayList<>();
        for (MerchantItemDTO merchantItemDTO : merchantItemDTOS) {
            List<ItemDetailDTO> itemDetailDTOS = merchantItemDTO.getItemDetailDTOList();
            if (CollectionUtils.isEmpty(itemDetailDTOS)) {
                continue;
            }

            for (Iterator<ItemDetailDTO> it = itemDetailDTOS.iterator(); it.hasNext();) {
                ItemDetailDTO itemDetailDTO = it.next();
                if (itemDetailDTO.getInvalid() != null && itemDetailDTO.getInvalid()) {
                    invalidItemDetailDTO.add(itemDetailDTO);
                    it.remove();
                }
            }
        }

        orderContext.setInvalidItemDetailDTOS(invalidItemDetailDTO);
    }
}
