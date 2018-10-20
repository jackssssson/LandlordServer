package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contentID")
    @JsonIgnore
    private MessageContent message_content;

    @ManyToOne
    @JoinColumn(name = "sender_userID")
    @JsonIgnore
    private User sender;

    @ManyToOne
    @JoinColumn(name = "recipient_userID")
    @JsonIgnore
    private User recipient;

    @Column(name = "time_stamp")
    private Date timeStamp;

    public Messages(MessageContent message_content, User sender, User recipient, Date timeStamp) {
        this.message_content = message_content;
        this.sender = sender;
        this.recipient = recipient;
        this.timeStamp = timeStamp;
    }

    public Messages() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public MessageContent getMessage_content() {
        return message_content;
    }

    public void setMessage_content(MessageContent message_content) {
        this.message_content = message_content;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }
}
