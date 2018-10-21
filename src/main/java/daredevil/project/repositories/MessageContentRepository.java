package daredevil.project.repositories;

import daredevil.project.models.MessageContent;

public interface MessageContentRepository {
    void createMessageContent(MessageContent messageContent);
    MessageContent getMessageContentId(int id);
    void updateMessageContent(int id, MessageContent messageContent);
    void deleteMessageContent(int id);
}
