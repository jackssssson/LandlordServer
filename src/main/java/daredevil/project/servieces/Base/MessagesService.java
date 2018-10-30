package daredevil.project.servieces.Base;

import daredevil.project.Exceptions.CantCreateMessageContentException;
import daredevil.project.Exceptions.CantCreateMessageException;
import daredevil.project.Exceptions.CantCreateUserException;
import daredevil.project.models.DTO.MessagesDTO;
import daredevil.project.models.Messages;
import daredevil.project.models.Models.MessagesModel;
import daredevil.project.models.User;

import java.util.List;

public interface MessagesService {
    List<Messages> getMessagessByUser(String username);
    void postMessage(MessagesModel message);
    void postMessage(MessagesModel message, String messageType) throws CantCreateMessageException, CantCreateMessageContentException, CantCreateUserException;
}
