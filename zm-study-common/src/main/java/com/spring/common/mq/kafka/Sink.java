package com.spring.common.mq.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Sink
{
    String INPUT = "input";

    @Input(Sink.INPUT)
    public SubscribableChannel input();
}
