package com.example.demo.params.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

/**
 * 商品详情
 * @author licong
 * @date 2021/3/6 下午9:09
 */
@Data
public class ItemDetailDTO {

    /**商品code*/
    private String itemCode;

    /**购买数量*/
    private Integer num;

    /**缩略图*/
    private String thumbImg;

    /**商品名称*/
    private String itemName;

    /**原始售价*/
    private Integer origPrice;

    /**实际售价*/
    private Integer realPrice;

    /**所有的优惠金额*/
    private Integer discountAmount = 0;

    /**运费*/
    private Integer shippingAmount = 0;

    /**价格id*/
    private Long priceId;

    /**活动id*/
    private Long activityId;

    /**商家id*/
    private Long merchantId;

    /**店铺id*/
    private Long shopId;

    /**商品是否无效*/
    private Boolean invalid;

    /**库存数量*/
    private Integer stock;

    /**购买该item使用的资源*/
    private List<OrderResourceDTO> orderResources = new ArrayList<>();

    /**优惠金额map，key为 ResourceEnum，value为优惠金额 */
    private Map<String,Integer> resourceDiscountMap = new HashMap<String, Integer>();

    public Integer sumItemRealPrice() {
        return this.num * this.realPrice;
    }

    /**
     * 添加使用的资源
     * @param resourceEnum
     * @param resourceId
     * @param amount
     */
    public void addOrderResource(ResourceEnum resourceEnum, Long resourceId, Integer amount) {
        OrderResourceDTO orderResourceDTO = new OrderResourceDTO();
        orderResourceDTO.setResourceType(resourceEnum.getResourceType());
        orderResourceDTO.setResourceId(resourceId);
        orderResourceDTO.setAmount(amount);
        this.orderResources.add(orderResourceDTO);
        if (resourceEnum.getDiscountFlag()) {
            this.discountAmount += amount;
            Integer resourceDiscountAmount = this.resourceDiscountMap.getOrDefault(resourceEnum.getResourceType(), 0);
            resourceDiscountAmount += amount;
            this.resourceDiscountMap.put(resourceEnum.getResourceType(), resourceDiscountAmount);
        }
    }

    /**
     * 获取指定优惠之前的金额
     * @return
     */
    public Integer getDiscountAmountBeforeResourceType(ResourceEnum resource){
        Integer discountAmountBeforeResourceType = this.sumItemRealPrice() - this.getDiscountAmount();
        if (resource != null) {
            discountAmountBeforeResourceType += this.getDiscountAmountByResourceType(resource);
        }
        return discountAmountBeforeResourceType;
    }

    /**
     * 获取传入的资源优惠金额
     * @param resourceEnum
     * @return
     */
    public Integer getDiscountAmountByResourceType(ResourceEnum resourceEnum){
        if(this.resourceDiscountMap.get(resourceEnum.getResourceType()) == null){
            return 0;
        }
        return this.resourceDiscountMap.get(resourceEnum.getResourceType());
    }


}
