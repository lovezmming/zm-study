package com.spring.common.mq.kafka;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Source
{
    String OUTPUT = "output";

    @Output(Source.OUTPUT)
    public MessageChannel output();
}
