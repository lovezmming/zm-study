/*
 * Copyright (c) 2018 上海极值信息技术有限公司 All Rights Reserved.
 */
package com.spring.common.util;

import com.spring.common.enums.ServiceIdEnum;

import java.util.Random;

/**
 * ServiceUtil.
 * @author <A HREF="mailto:zming@extremevalue.cn">Ming.Zhu</A>
 * @version 1.0, $Revision: 0$, $Date: 2018年12月4日$
 * @since 1.0
 */
public class ServiceUtil
{
    private static Random random = new Random();

    /**
     * get id
     * @param userId
     * @param userId
     * @return
     */
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
