package daredevil.project.models.Models;

public class MessagesModel {
    private int senderId;
    private int recipientId;
    private byte[] imageMessage;

    public MessagesModel() {
    }

    public MessagesModel(int senderId, int recipientId, byte[] imageMessage) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.imageMessage = imageMessage;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    }

    public byte[] getImageMessage() {
        return imageMessage;
    }

    public void setImageMessage(byte[] imageMessage) {
        this.imageMessage = imageMessage;
    }
}
