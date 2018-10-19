package com.example.demo.models;

import com.mysql.jdbc.Blob;

import javax.persistence.*;

@Entity
@Table(name = "message_content")
public class MessageContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contentID")
    private int id;

    @Column(name = "contentTypeID")
    private int contentTypeId;

    @Column(name = "image")
    private Blob image;

    @Column(name = "textMessage")
    private String textMessage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContentTypeId() {
        return contentTypeId;
    }

    public void setContentTypeId(int contentTypeId) {
        this.contentTypeId = contentTypeId;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }
}
