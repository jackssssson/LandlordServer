package daredevil.project.servieces;

import daredevil.project.Exceptions.CantCreateMessageContentException;
import daredevil.project.Exceptions.CantCreateMessageException;
import daredevil.project.Exceptions.CantCreateUserException;
import daredevil.project.models.Messages;
import daredevil.project.models.Models.MessagesModel;
import daredevil.project.repositories.base.MessagesRepository;
import daredevil.project.repositories.base.UserRepository;
import daredevil.project.servieces.Base.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessagesServiceImpl implements MessagesService {
    private UserRepository userRepository;
    private MessagesRepository messagesRepository;

    @Autowired
    public MessagesServiceImpl(UserRepository userRepository, MessagesRepository messagesRepository) {
        this.userRepository = userRepository;
        this.messagesRepository = messagesRepository;
    }

    @Override
    public List<Messages> getMessagessByUser(String userName) {
        return null;
    }

    @Override
    public void postMessage(MessagesModel message) {

    }

    @Override
    public void postMessage(MessagesModel message, String messageType) throws CantCreateMessageException, CantCreateMessageContentException, CantCreateUserException {
        Date date=new Date();
        Messages messages=new Messages(message.getTextMessage(), userRepository.getUserByName(message.getSenderName()), userRepository.getUserByName(message.getRecipientName()), date);
        messagesRepository.postMesssage(messages);

    }
}
