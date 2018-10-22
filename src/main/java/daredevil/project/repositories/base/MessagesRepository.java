package daredevil.project.repositories.base;

import daredevil.project.Exceptions.CantCreateMessageException;
import daredevil.project.models.Messages;

import java.util.List;

public interface MessagesRepository {
    void createMessages(Messages messages);
    Messages getMessagesById(int id);
    void updateMessages(int id, Messages messages);
    void deleteMessages(int id);
    List<Messages> getMessageByUserName(String userName);
    public void postMesssage(Messages messages) throws CantCreateMessageException;
}
