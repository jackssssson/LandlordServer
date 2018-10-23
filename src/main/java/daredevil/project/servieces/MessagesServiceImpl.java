package daredevil.project.servieces;

import daredevil.project.Exceptions.CantCreateMessageContentException;
import daredevil.project.Exceptions.CantCreateMessageException;
import daredevil.project.models.ContentType;
import daredevil.project.models.DTO.MessagesDTO;
import daredevil.project.models.MessageContent;
import daredevil.project.models.Messages;
import daredevil.project.models.Models.MessagesModel;
import daredevil.project.models.User;
import daredevil.project.repositories.base.ContentTypeRepository;
import daredevil.project.repositories.base.MessageContentRepository;
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
    private MessageContentRepository messageContentRepository;
    private ContentTypeRepository contentTypeRepository;

    @Autowired
    public MessagesServiceImpl(UserRepository userRepository, MessagesRepository messagesRepository, MessageContentRepository messageContentRepository, ContentTypeRepository contentTypeRepository) {
        this.userRepository = userRepository;
        this.messagesRepository = messagesRepository;
        this.messageContentRepository=messageContentRepository;
        this.contentTypeRepository=contentTypeRepository;
    }

    @Override
    public List<Messages> getMessagessByUser(String userName) {
        return null;
    }

    @Override
    public void postMessage(MessagesModel message) {

    }

    @Override
    public void postMessage(MessagesModel message, String messageType) throws CantCreateMessageException, CantCreateMessageContentException {
        Date date=new Date();
        ContentType contentType=contentTypeRepository.getContentTypeByName(messageType);
        Messages messages=new Messages(userRepository.getUserByName(message.getSenderName()), userRepository.getUserByName(message.getRecipientName()), date, contentType);
        MessageContent messageContent=messages.getMessage_content();
        messageContent.setTextMessage(message.getTextMessage());
        messageContentRepository.createMessageContent(messageContent);
        //messagesRepository.postMesssage(messages);

    }
}
