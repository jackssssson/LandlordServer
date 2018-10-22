package daredevil.project.models.Models;

import java.util.Date;

public class MessagesModel {
    private String textMessage;
    private String senderName;
    private String recipientName;

    public MessagesModel() {
    }

    public MessagesModel( String textMessage, String senderName, String recipientName) {
        this.textMessage = textMessage;
        this.senderName = senderName;
        this.recipientName = recipientName;
    }


    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }
}
