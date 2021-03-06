package daredevil.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.type.BlobType;
import org.hibernate.type.descriptor.sql.LobTypeMappings;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private int id;

    @ManyToOne
    @JoinColumn(name = "sender_userID", nullable = false)
    @JsonIgnore
    private User sender;

    @ManyToOne
    @JoinColumn(name = "recipient_userID", nullable = false)
    @JsonIgnore
    private User recipient;

    @Column(name = "time_stamp")
    private Date timeStamp;

    @Column(name = "message_type", nullable = false)
    private String messageType;

    @Column(name = "text_message")
    private String textMessage;

    @ManyToOne
    @JoinColumn(name = "estate_id")
    @JsonIgnore
    private Estates estates;

    @Lob
    @Column(name = "image_Message", columnDefinition = "LongBLOB")
    private byte[] imageMessage;

    @Column(name = "seen")
    private boolean seen;

    public Messages(User sender, User recipient, Date timeStamp, String textMessage, Estates estates, boolean seen) {
        this.sender = sender;
        this.recipient = recipient;
        this.timeStamp = timeStamp;
        this.messageType = "Estate message";
        this.textMessage = textMessage;
        this.estates = estates;
        this.imageMessage = null;
        this.seen = seen;
    }

    public Messages(String textMessage, User sender, User recipient, Date timeStamp, boolean seen) {
        this.textMessage = textMessage;
        this.sender = sender;
        this.recipient = recipient;
        this.timeStamp = timeStamp;
        this.messageType="Text message";
        this.imageMessage=null;
        this.seen=seen;
        this.estates=null;
    }

    public Messages(byte[] imageMessage, User sender, User recipient, Date timeStamp, boolean seen) {
        this.imageMessage = imageMessage;
        this.sender = sender;
        this.recipient = recipient;
        this.timeStamp = timeStamp;
        this.messageType="Image message";
        this.textMessage=null;
        this.seen=seen;
        this.estates=null;
    }

    public Messages( User sender, User recipient, Date timeStamp, String messageType) {
        this.messageType=messageType;
        this.sender = sender;
        this.recipient = recipient;
        this.timeStamp = timeStamp;
        this.imageMessage=null;
        this.imageMessage=null;
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

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public byte[] getImageMessage() {
        return imageMessage;
    }

    public void setImageMessage(byte[] imageMessage) {
        this.imageMessage = imageMessage;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public Estates getEstates() {
        return estates;
    }

    public void setEstates(Estates estates) {
        this.estates = estates;
    }
}
