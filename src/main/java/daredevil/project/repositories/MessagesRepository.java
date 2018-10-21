package daredevil.project.repositories;

import daredevil.project.models.Messages;

public interface MessagesRepository {
    void createMessages(Messages messages);
    Messages getMessagesById(int id);
    void updateMessages(int id, Messages messages);
    void deleteMessages(int id);
}
