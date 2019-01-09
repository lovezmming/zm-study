package com.spring.common.util;

import com.spring.common.enums.ServiceIdEnum;

import java.util.Random;

public class ServiceUtil
{
    private static Random random = new Random();

    public static String getId(String userId)
    {
        String newId = "1" 
                + TextUtil.expandNum(TextUtil.isEmpty(userId) ? Integer.valueOf("000001") : Integer.valueOf(userId.substring(1, 7)), "0", 6) 
                + TextUtil.expandNum(Integer.valueOf(ServiceIdEnum.zm_study.getValue()), "0", 6)
                + TextUtil.expandNum(random.nextInt(999999), "0", 6)
                + System.currentTimeMillis();
        return newId;
    }

}
