package daredevil.project.repositories.base;

import daredevil.project.Exceptions.CantCreateMessageException;
import daredevil.project.Exceptions.NoNewMessagesEception;
import daredevil.project.models.DTO.MessagesDTO;
import daredevil.project.models.Messages;

import java.util.List;

public interface MessagesRepository {
    void createMessages(Messages messages);
    Messages getMessagesById(int id);
    void updateMessages(int id, Messages messages);
    void deleteMessages(int id);
    List<Messages> getMessageByUserName(String userName);
    void postMesssage(Messages messages) throws CantCreateMessageException;
    boolean checkForNewMessagess(int sender, int recipient);
    List<Messages> getNewMessagess(int sender, int recipient);
    boolean checkForMessages(int sender, int recipient);
    List<Messages> getMessagess(int sender, int recipient);
    List<Messages> getEstateMessages(int sender, int recipient, int estate);
    boolean checkForEstateMessages(int sender, int recipient, int estate);
    Messages getImageMessageForSession(int recipient, int sender) throws NoNewMessagesEception;
}
