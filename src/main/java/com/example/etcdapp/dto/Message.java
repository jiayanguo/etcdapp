package com.example.etcdapp.dto;

import java.io.Serializable;
import java.util.Objects;

public class Message implements Serializable {
    private String name;
    private String body;

    public Message(String name, String body) {
        this.name = name;
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(name, message.name) &&
                Objects.equals(body, message.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, body);
    }

    @Override
    public String toString() {
        return "Message{" +
                "name='" + name + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
