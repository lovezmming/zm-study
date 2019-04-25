package com.spring.common.mq.kafka.consumer;

import com.spring.common.mq.kafka.Sink;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(Sink.class)
public class MessageConsumer
{
    @StreamListener(Sink.INPUT)
    public void messageSink(Object payload) {
        System.out.println("Received: " + payload);
    }

}
