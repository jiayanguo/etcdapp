package com.example.etcdapp.etcd;

import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DbInitializer {
    @Value("${etcd.url}")
    String etcdUrl;

    public KV initDB() {
        Client client = Client.builder().endpoints(etcdUrl)
                .build();
        KV kvClient = client.getKVClient();
        return kvClient;
    }
}
