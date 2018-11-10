package daredevil.project.servieces;

import daredevil.project.Exceptions.CantCreateMessageException;
import daredevil.project.Exceptions.NoEstateFoundException;
import daredevil.project.Exceptions.NoNewMessagesEception;
import daredevil.project.Exceptions.NoUserFoundException;
import daredevil.project.models.DTO.MessagesDTO;
import daredevil.project.models.Estates;
import daredevil.project.models.Messages;
import daredevil.project.models.User;
import daredevil.project.models.Models.MessagesModel;
import daredevil.project.repositories.base.EstatesRepository;
import daredevil.project.repositories.base.MessagesRepository;
import daredevil.project.repositories.base.UserRepository;
import daredevil.project.servieces.Base.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessagesServiceImpl implements MessagesService {
    private UserRepository userRepository;
    private MessagesRepository messagesRepository;
    private EstatesRepository estatesRepository;

    @Autowired
    public MessagesServiceImpl(UserRepository userRepository, MessagesRepository messagesRepository, EstatesRepository estatesRepository) {
        this.userRepository = userRepository;
        this.messagesRepository = messagesRepository;
        this.estatesRepository=estatesRepository;
    }

    @Override
    public List<Messages> getMessagessByUser(String userName) {
        return null;
    }


    @Override
    public boolean checkForNewMessagess(int sender, int recipient){
        return messagesRepository.checkForNewMessagess(sender, recipient);
    }
    @Override
    public List<MessagesDTO> getNewMessagess(int sender, int recipient){
        List<Messages> messages=messagesRepository.getNewMessagess(sender, recipient);
        for(Messages m: messages){
            m.setSeen(true);
            messagesRepository.updateMessages(m.getId(), m);
        }
        List<MessagesDTO> result=new ArrayList<>();
        for(Messages m: messages){
            result.add(MessagesDTO.getFromMessages(m));
        }
        return result;
    }

    @Override
    public boolean checkForMessagess(int sender, int recipient){
        return messagesRepository.checkForMessages(sender, recipient);
    }
    @Override
    public List<MessagesDTO> getMessagess(int sender, int recipient){
        List<Messages> messages=messagesRepository.getMessagess(sender, recipient);
        for(Messages m: messages){
            m.setSeen(true);
            messagesRepository.updateMessages(m.getId(), m);
        }
        List<MessagesDTO> result=new ArrayList<>();
        for(Messages m: messages){
            result.add(MessagesDTO.getFromMessages(m));
        }
        return result;
    }

    @Override
    public MessagesDTO postTextMessage(String message, int sender, int recipient) throws NoUserFoundException, CantCreateMessageException {
        Messages messages=new Messages(message, userRepository.getUserById(sender), userRepository.getUserById(recipient), new Date(), false);
        messagesRepository.postMesssage(messages);
        return MessagesDTO.getFromMessages(messages);
    }

    @Override
    public void postImageMessage(MessagesModel imageMessage) throws NoUserFoundException, CantCreateMessageException {
        Messages messages=new Messages(imageMessage.getImageMessage()
                , userRepository.getUserById(imageMessage.getSenderId())
                , userRepository.getUserById(imageMessage.getRecipientId())
                , new Date(), false);
        Messages updateThis= null;
        try {
            updateThis = messagesRepository.getImageMessageForSession(imageMessage.getRecipientId(), imageMessage.getSenderId());
            messagesRepository.updateMessages(updateThis.getId(), messages);
        } catch (NoNewMessagesEception noNewMessagesEception) {
            messagesRepository.postMesssage(messages);
        }

    }

    @Override
    public List<MessagesDTO> getEstateMessages(int estate) throws NoEstateFoundException {
        List<MessagesDTO> result=new ArrayList<>();
        Estates estates=estatesRepository.getEstateById(estate);
        for(Messages m:messagesRepository.getEstateMessages(estates.getLandlord().getId(), estates.getTenant().getId(), estate)){
            result.add(MessagesDTO.getFromMessages(m));
        }
        return result;
    }

    @Override
    public boolean checkForEstateMessages(int estate) throws NoEstateFoundException {
        Estates estates=estatesRepository.getEstateById(estate);
        return messagesRepository.checkForEstateMessages(estates.getLandlord().getId(), estates.getTenant().getId(), estate);
    }

    @Override
    public String postEstateMessage(String message, int estateID, int senderID) throws NoEstateFoundException, NoUserFoundException, CantCreateMessageException {
        Estates estates=estatesRepository.getEstateById(estateID);
        User recipient;
        if(estates.getTenant().getId()==senderID)
            recipient=estates.getLandlord();
        else{
            recipient=estates.getTenant();
        }
        Messages messages=new Messages(userRepository.getUserById(senderID), recipient, new Date(), message, estates,true);
        messagesRepository.postMesssage(messages);
        return " \" "+message+"\" sent.";
    }
}
