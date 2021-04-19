package com.example.demo.components.discount;

import com.example.demo.params.internal.ItemDetailDTO;

import com.example.demo.params.internal.MerchantItemDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.demo.params.internal.ResourceEnum;
import com.example.demo.utils.MathUtils;
import org.springframework.util.CollectionUtils;

/**
 * 营销相关操作
 */
public abstract class AbstractDiscountOperation {

    /**
     * 获取所有店铺下的有效item
     * @param merchantItemDTOS
     * @return
     */
    public List<ItemDetailDTO> getValidItems(List<MerchantItemDTO> merchantItemDTOS) {
        List<ItemDetailDTO> itemDetailDTOS = new ArrayList<>();
        if (CollectionUtils.isEmpty(merchantItemDTOS)) {
            return itemDetailDTOS;
        }

        merchantItemDTOS.stream()
            .filter(merchantItem -> !CollectionUtils.isEmpty(merchantItem.getItemDetails()))
            .map(merchantItem -> itemDetailDTOS.addAll(merchantItem.getItemDetails()))
            .collect(Collectors.toList());

        return itemDetailDTOS;

    }

    /**
     * 指定优惠前总商品金额
     * @return
     */
    private Integer getSumItemAmount(List<ItemDetailDTO> itemDetailDTOList, ResourceEnum resource){
        Integer sumItemAmount = 0;
        for(ItemDetailDTO itemDetail : itemDetailDTOList){
            sumItemAmount += itemDetail.getDiscountAmountBeforeResourceType(resource);
        }
        return sumItemAmount;
    }

    /**
     * 分摊优惠金额
     * @param itemDetailDTOList
     * @param sumDiscountAmount
     */
    public void distributeDiscount(List<ItemDetailDTO> itemDetailDTOList, Integer sumDiscountAmount, Long resourceId, ResourceEnum resource, Object extInfo){

        Integer sumItemAmount = this.getSumItemAmount(itemDetailDTOList,resource);
        Integer tempSumDiscountAmount = sumDiscountAmount;

        /**按优惠券之前的金额摊*/
        Map<String,Integer> itemDiscountMap = new HashMap<>();
        for(ItemDetailDTO itemDetailDTO : itemDetailDTOList){
            int perDiscountAmount = itemDetailDTO.getDiscountAmountBeforeResourceType(resource);
            perDiscountAmount = MathUtils.getIntByMathFloor(perDiscountAmount*1.0 * tempSumDiscountAmount/sumItemAmount);
            sumDiscountAmount = sumDiscountAmount - perDiscountAmount;
            itemDiscountMap.put( itemDetailDTO.getItemCode() ,perDiscountAmount);
        }

        if(sumDiscountAmount > 0){
            for(ItemDetailDTO itemDetailDTO : itemDetailDTOList){
                if(sumDiscountAmount < 1){
                    break;
                }
                //perDiscountAmount为每个商品还可以减免金额
                int perDiscountAmount = sumDiscountAmount;
                //当前商品最大还可以减免金额
                int maxDiscountPrice = itemDetailDTO.getDiscountAmountBeforeResourceType(resource);
                if(itemDiscountMap.containsKey(itemDetailDTO.getItemCode())){
                    maxDiscountPrice = maxDiscountPrice - itemDiscountMap.get(itemDetailDTO.getItemCode());
                }
                //优惠的金额大于最大可以优惠金额，则能减多少减多少，剩余的在下一件商品减
                if(perDiscountAmount > maxDiscountPrice){
                    perDiscountAmount = maxDiscountPrice;
                }

                sumDiscountAmount -= perDiscountAmount;

                /**优惠金额跟第一次的加一起*/
                Integer orgDiscountAmount = itemDiscountMap.get(itemDetailDTO.getItemCode());
                if(orgDiscountAmount == null){
                    orgDiscountAmount = 0;
                }
                itemDiscountMap.put(itemDetailDTO.getItemCode(),orgDiscountAmount + perDiscountAmount);
            }
        }

        /**汇总金额，增加Resource*/
        for(ItemDetailDTO itemDetailDTO : itemDetailDTOList){
            if(itemDiscountMap.containsKey(itemDetailDTO.getItemCode()) && itemDiscountMap.get(itemDetailDTO.getItemCode()) > 0){
                //插入优惠金额的Resource
                Integer perItemDiscountAmount = itemDiscountMap.get(itemDetailDTO.getItemCode());
                itemDetailDTO.addOrderResource(resource, resourceId, perItemDiscountAmount);
            }
        }
    }


}
