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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contentID")
    private MessageContent message_content;

    @Column(name = "time_stamp")
    private Date timeStamp;

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
}
