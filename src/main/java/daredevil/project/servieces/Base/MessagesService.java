package daredevil.project.servieces.Base;

import daredevil.project.Exceptions.CantCreateMessageContentException;
import daredevil.project.Exceptions.CantCreateMessageException;
import daredevil.project.Exceptions.CantCreateUserException;
import daredevil.project.Exceptions.NoUserFoundException;
import daredevil.project.models.DTO.MessagesDTO;
import daredevil.project.models.Messages;
import daredevil.project.models.Models.MessagesModel;

import java.util.List;

public interface MessagesService {
    List<Messages> getMessagessByUser(String username);

    boolean checkForNewMessagess(int sender, int recipient);

    List<MessagesDTO> getNewMessagess(int sender, int recipient);

    boolean checkForMessagess(int sender, int recipient);

    List<MessagesDTO> getMessagess(int sender, int recipient);

    MessagesDTO postTextMessage(String message, int sender, int recipient) throws NoUserFoundException, CantCreateMessageException;

    void postImageMessage(MessagesModel imageMessage) throws NoUserFoundException, CantCreateMessageException;
}
