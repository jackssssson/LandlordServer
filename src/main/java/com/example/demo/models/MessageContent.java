package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "message_content")
public class MessageContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contentID")
    private int id;

    @Column(name = "image")
    private byte[] image;

    @Column(name = "textMessage")
    private String textMessage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contentTypeID", nullable = false)
    @JsonIgnore
    private ContentType content_types;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "message_content")
    private Messages messages;

    public MessageContent(byte[] image, String textMessage, ContentType content_types, Messages messages) {
        this.image = image;
        this.textMessage = textMessage;
        this.content_types = content_types;
        this.messages = messages;
    }

    public MessageContent() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public ContentType getContent_types() {
        return content_types;
    }

    public void setContent_types(ContentType content_types) {
        this.content_types = content_types;
    }

    public Messages getMessages() {
        return messages;
    }

    public void setMessages(Messages messages) {
        this.messages = messages;
    }
}
