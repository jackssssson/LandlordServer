package daredevil.project.models.DTO;

public class MessageContentDTO {
    private MessagesContentDTO contentType;
    private String textMessage;

    public MessageContentDTO() {
    }

    public MessageContentDTO(MessagesContentDTO contentType, String textMessage) {
        this.contentType = contentType;
        this.textMessage = textMessage;
    }

    public MessagesContentDTO getContentType() {
        return contentType;
    }

    public void setContentType(MessagesContentDTO contentType) {
        this.contentType = contentType;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }
}
