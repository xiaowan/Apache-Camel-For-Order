package com.example.demo.routes.predicate;

import com.example.demo.routes.dto.OrderContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author licong
 * @date 2021/3/8 下午12:53
 */
public class UseDiscountPredicate {

    public String useDiscount(OrderContext orderContext) {
        List<String> beans = new ArrayList<>();
        beans.add("bean:useMemberDiscountComponent");
        beans.add("bean:useCouponComponent");
        beans.add("bean:useFundDiscountComponent");
        beans.add("bean:useGoldDiscountComponent");
        Collections.shuffle(beans);
        return beans.stream().collect(Collectors.joining(","));
    }
}
