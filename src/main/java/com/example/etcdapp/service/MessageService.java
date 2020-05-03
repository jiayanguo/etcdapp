package com.example.etcdapp.service;

import com.example.etcdapp.dao.MessageDao;
import com.example.proto.model.Message;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageService {
    Logger logger = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    MessageDao messageDao;

    public Message.DemoMessage get(String key) {
        try {
            return messageDao.get(key);
        } catch (Exception e) {
            logger.error("Error", e);
            throw new RuntimeException();
        }
    }

    public void delete(String key) {
        try {
             messageDao.delete(key);
        } catch (Exception e) {
            logger.error("Error", e);
            throw new RuntimeException();
        }
    }

    public void add(String key, String name, String body) {
        try {
            Message.DemoMessage message = Message.DemoMessage.newBuilder()
                    .setName(name)
                    .setBody(body)
                    .build();
            messageDao.add(key, message);
        } catch (Exception e) {
            logger.error("Error", e);
            throw new RuntimeException();
        }
    }

    public void update(String key, String name, String body) {
        try {

            Message.DemoMessage message = get(key);

            if (message != null) {
                Message.DemoMessage.Builder builder = message.toBuilder();
                if (!StringUtil.isNullOrEmpty(name)) {
                    builder.setName(name);
                }

                if (!StringUtil.isNullOrEmpty(body)) {
                    builder.setBody(body);
                }

                messageDao.update(key, builder.build());
            }else {
                logger.error("unknown key: {}", key);
                throw new RuntimeException();
            }
        } catch (Exception e) {
            logger.error("Error", e);
            throw new RuntimeException();
        }
    }

}
