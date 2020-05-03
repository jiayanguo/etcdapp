package com.example.etcdapp.dao;

import com.example.proto.model.Message;
import com.google.protobuf.InvalidProtocolBufferException;

import java.util.concurrent.ExecutionException;

public interface MessageDao {
    Message.DemoMessage get(String key) throws ExecutionException, InterruptedException, InvalidProtocolBufferException;
    void delete(String key) throws ExecutionException, InterruptedException;
    void add(String key, Message.DemoMessage demoMessage) throws ExecutionException, InterruptedException;
    void update(String key, Message.DemoMessage demoMessage) throws ExecutionException, InterruptedException;
}
