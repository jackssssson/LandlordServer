package daredevil.project.repositories.base;

import daredevil.project.Exceptions.CantCreateMessageContentException;
import daredevil.project.models.MessageContent;

public interface MessageContentRepository {
    void createMessageContent(MessageContent messageContent) throws CantCreateMessageContentException;
    MessageContent getMessageContentId(int id);
    void updateMessageContent(int id, MessageContent messageContent);
    void deleteMessageContent(int id);
}
