package daredevil.project.servieces.Base;

import daredevil.project.Exceptions.*;
import daredevil.project.models.DTO.MessagesDTO;
import daredevil.project.models.Models.MessagesModel;

import java.util.List;

public interface MessagesService {

    boolean checkForNewMessages(int sender, int recipient);

    List<MessagesDTO> getNewMessages(int sender, int recipient);

    boolean checkForMessages(int sender, int recipient);

    List<MessagesDTO> getMessages(int sender, int recipient);

    MessagesDTO postTextMessage(String message, int sender, int recipient) throws NoUserFoundException, CantCreateMessageException;

    void postImageMessage(MessagesModel imageMessage) throws NoUserFoundException, CantCreateMessageException, NoNewMessagesEception;

    List<MessagesDTO> getEstateMessages(int estate) throws NoEstateFoundException;

    boolean checkForEstateMessages(int estate) throws NoEstateFoundException;

    String postEstateMessage(String message, int estateID, int senderID) throws NoEstateFoundException, NoUserFoundException, CantCreateMessageException;

}
