package com.example.demo.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "content_types")
public class ContentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contentTypeID")
    private int id;

    @Column(name = "contentType")
    private String contentType;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "content_types")
    private List<MessageContent> message_content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public List<MessageContent> getMessage_content() {
        return message_content;
    }

    public void setMessage_content(List<MessageContent> message_content) {
        this.message_content = message_content;
    }
}
