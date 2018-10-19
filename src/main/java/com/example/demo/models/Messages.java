package com.example.demo.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageID")
    private int id;

    @Column(name = "sender_userID")
    private int senderId;

    @Column(name = "contentID")
    private int contentId;

    @Column(name = "recepient_userID")
    private int recepient;

    @Column(name = "time_stamp")
    private Date timeStamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public int getRecepient() {
        return recepient;
    }

    public void setRecepient(int recepient) {
        this.recepient = recepient;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
