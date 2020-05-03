package com.example.etcdapp.dao;

import com.example.etcdapp.etcd.DbInitializer;
import com.example.proto.model.Message;
import com.google.protobuf.InvalidProtocolBufferException;
import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.kv.GetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Repository
public class MessageDaoImpl implements MessageDao{

    private KV kvClient;

    public MessageDaoImpl(@Autowired DbInitializer initializer) {
        kvClient = initializer.initDB();
    }

    @Override
    public Message.DemoMessage get(String key) throws ExecutionException, InterruptedException, InvalidProtocolBufferException {
        ByteSequence keySeq = ByteSequence.from(key.getBytes());
        CompletableFuture<GetResponse> getFuture = kvClient.get(keySeq);
        GetResponse response = getFuture.get();
        byte[] data = response.getKvs().get(0).getValue().getBytes();

        return Message.DemoMessage.parseFrom(data);
    }

    @Override
    public void delete(String key) throws ExecutionException, InterruptedException {
        ByteSequence keySeq = ByteSequence.from(key.getBytes());
        kvClient.delete(keySeq).get();

    }

    @Override
    public void add(String key, Message.DemoMessage demoMessage) throws ExecutionException, InterruptedException {
        ByteSequence keySeq = ByteSequence.from(key.getBytes());
        ByteSequence value = ByteSequence.from(demoMessage.toByteArray());
        kvClient.put(keySeq, value).get();
    }

    @Override
    public void update(String key, Message.DemoMessage demoMessage) throws ExecutionException, InterruptedException {
        ByteSequence keySeq = ByteSequence.from(key.getBytes());
        ByteSequence value = ByteSequence.from(demoMessage.toByteArray());
        kvClient.put(keySeq, value).get();
    }
}
