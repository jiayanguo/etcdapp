package com.example.etcdapp.converter;

import com.example.etcdapp.dto.Message;

public class MessageProtoToMessageDto {

    public static Message convert (com.example.proto.model.Message.DemoMessage demoMessage) {
        Message message = new Message(demoMessage.getName(), demoMessage.getBody());
        return message;
    }
}
