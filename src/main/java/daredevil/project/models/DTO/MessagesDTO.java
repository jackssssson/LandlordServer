package daredevil.project.models.DTO;

import java.util.Date;

public class MessagesDTO {
    private Date timeStamp;
    private MessagesContentDTO messagesContent;
    private UserDTO sender;
    private UserDTO recipient;

    public MessagesDTO() {
    }

    public MessagesDTO(Date timeStamp, MessagesContentDTO messagesContent, UserDTO sender, UserDTO recipient) {
        this.timeStamp = timeStamp;
        this.messagesContent = messagesContent;
        this.sender = sender;
        this.recipient = recipient;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public MessagesContentDTO getMessagesContent() {
        return messagesContent;
    }

    public void setMessagesContent(MessagesContentDTO messagesContent) {
        this.messagesContent = messagesContent;
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
}
