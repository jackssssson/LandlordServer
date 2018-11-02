package daredevil.project.models.DTO;

import java.util.Date;

public class MessagesDTO {
    private Date timeStamp;
    private UserDTO sender;
    private UserDTO recipient;
    private String textMessage;
    private byte[] imageMessage;
    private String messageType;
    public MessagesDTO() {
    }

    public MessagesDTO(Date timeStamp, UserDTO sender, UserDTO recipient, String textMessage, byte[] imageMessage, String messageType) {
        this.timeStamp = timeStamp;
        this.sender = sender;
        this.recipient = recipient;
        this.textMessage = textMessage;
        this.imageMessage = imageMessage;
        this.messageType = messageType;
    }

    public MessagesDTO(Date timeStamp, String textMessage, UserDTO sender, UserDTO recipient) {
        this.timeStamp = timeStamp;
        this.sender = sender;
        this.recipient = recipient;
        this.textMessage=textMessage;
        this.messageType="Text message";
    }

    public MessagesDTO(Date timeStamp, byte[] imageMessage, UserDTO sender, UserDTO recipient) {
        this.timeStamp = timeStamp;
        this.sender = sender;
        this.recipient = recipient;
        this.imageMessage=imageMessage;
        this.messageType="Image message";
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }


    public UserDTO getSender() {
        return sender;
    }

    public void setSender(UserDTO sender) {
        this.sender = sender;
    }

    public UserDTO getRecipient() {
        return recipient;
    }

    public void setRecipient(UserDTO recipient) {
        this.recipient = recipient;
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

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
