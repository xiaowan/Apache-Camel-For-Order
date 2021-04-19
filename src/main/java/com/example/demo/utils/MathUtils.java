package com.example.demo.utils;

/**
 * @author licong
 * @date 2021/4/19 23:59
 */
public abstract class MathUtils {

    /**
     * 向下取值
     * @param number
     * @return
     */
    public static int getIntByMathFloor(double number){
        String numStr=Math.floor(number)+"";
        return Integer.valueOf(numStr.substring(0,numStr.indexOf(".")));
    }

}
