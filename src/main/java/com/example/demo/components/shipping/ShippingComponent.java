package com.example.demo.components.shipping;

import com.example.demo.components.order.ShopComponent;
import com.example.demo.params.internal.ItemDetailDTO;
import com.example.demo.params.internal.MerchantItemDTO;
import com.example.demo.params.internal.TotalInfoDTO;
import com.example.demo.routes.dto.IProcessor;
import com.example.demo.routes.dto.OrderContext;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import org.springframework.util.CollectionUtils;

/**
 * 运费组件（获取运费，均摊运费）
 * @author licong
 * @date 2021/3/12 下午2:40
 */
@Component
public class ShippingComponent implements IProcessor<OrderContext> {

    @Autowired
    private ShopComponent shopComponent;

    @Override
    public void execute(OrderContext orderContext, Exchange exchange) throws Exception {

        /**将item纬度金额汇总至商家纬度*/
        shopComponent.calculateMerchantTotalInfo(orderContext.getMerchantItemDTOS());

        /**调用TMS获取运费*/
        this.getShippingAmount(orderContext.getMerchantItemDTOS());

        /**均摊运费*/
        this.distrubuteShippingAmount(orderContext.getMerchantItemDTOS());
    }

    private void getShippingAmount(List<MerchantItemDTO> merchantItemDTOS) {
        Map<Long, Integer> shippingAmount = new HashMap<>();
        if (CollectionUtils.isEmpty(merchantItemDTOS)) {
            return;
        }

        /**调用TMS获取运费,将运费设置到MerchantItem中*/
        for (MerchantItemDTO merchantItemDTO : merchantItemDTOS) {
            merchantItemDTO.getTotalInfoDTO().setShippingAmount(ThreadLocalRandom.current().nextInt(40, 80));
        }

    }

    /**
     * 分摊商家运费
     * @param merchantItemDTOList
     */
    private void distrubuteShippingAmount(List<MerchantItemDTO> merchantItemDTOList) {
        if (CollectionUtils.isEmpty(merchantItemDTOList)) {
            return;
        }
        for (MerchantItemDTO merchantItemDTO : merchantItemDTOList) {
            TotalInfoDTO totalInfoDTO = merchantItemDTO.getTotalInfoDTO();
            int remainShippingAmount = totalInfoDTO.getShippingAmount();
            int index = 1;
            for (ItemDetailDTO itemDetailDTO : merchantItemDTO.getItemDetailDTOList()) {
                BigDecimal shippingRate = new BigDecimal(itemDetailDTO.sumItemRealPrice() - itemDetailDTO.getDiscountAmount());
                if (shippingRate.doubleValue() > 0) {
                    shippingRate = shippingRate.divide(BigDecimal.valueOf(totalInfoDTO.getOrderAmount()), 2, BigDecimal.ROUND_HALF_UP);
                }

                int itemShippingAmount = shippingRate.multiply(BigDecimal.valueOf(totalInfoDTO.getShippingAmount())).setScale(2, BigDecimal.ROUND_HALF_UP).intValue();
                /**运费大于剩余运费，或者最后一件商品单独处理*/
                if (remainShippingAmount < itemShippingAmount || index == merchantItemDTO.getItemDetailDTOList().size()) {
                    itemShippingAmount = remainShippingAmount;
                }

                itemDetailDTO.setShippingAmount(itemShippingAmount);
                remainShippingAmount -= itemShippingAmount;
                index ++;
            }
        }
    }


}
