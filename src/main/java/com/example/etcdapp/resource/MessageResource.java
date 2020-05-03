package com.example.etcdapp.resource;

import com.example.etcdapp.converter.MessageProtoToMessageDto;
import com.example.etcdapp.dto.Message;
import com.example.etcdapp.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageResource {

    @Autowired
    MessageService service;

    @GetMapping("/message/{key}")
    public Message getMessage(
            @PathVariable("key") String key
    ){

        return MessageProtoToMessageDto.convert(service.get(key));
    }

    @PostMapping("/message/{key}")
    public String updateMessage(
            @PathVariable("key") String key,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "body", required = false) String body
    ){

        service.update(key, name, body);
        return "Updated!";
    }

    @PutMapping("/message/{key}")
    public String addMessage(
            @PathVariable("key") String key,
            @RequestParam("name") String name,
            @RequestParam("body") String body
    ){

        service.add(key, name, body);
        return "Added!";
    }

    @DeleteMapping("/message/{key}")
    public String addMessage(
            @PathVariable("key") String key
    ){

        service.delete(key);
        return "Delete!";
    }
}
