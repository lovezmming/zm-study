/*
 * Copyright (c) 2017 上海极值信息技术有限公司 All Rights Reserved.
 */
package com.spring.common.util;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

public class ElasticSearchUtil
{

    public TransportClient connect(String ip, Integer port)
    {
        try
        {
            Settings settings = Settings.builder().put("cluster.name", "mycluster") // 集群名
                    .put("client.transport.sniff", true) // 开启嗅探
                    .build();
            TransportClient client =
                    new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ip), port));
            return client;
        } catch (Exception e)
        {
        }
        return null;
    }

    public void destory(TransportClient client)
    {
        if (client != null)
        {
            client.close();
            client = null;
        }
    }
}
