package daredevil.project;

import daredevil.project.Exceptions.CantCreateMessageException;
import daredevil.project.Exceptions.NoEstateFoundException;
import daredevil.project.Exceptions.NoNewMessagesEception;
import daredevil.project.Exceptions.NoUserFoundException;
import daredevil.project.models.DTO.MessagesDTO;
import daredevil.project.models.Estates;
import daredevil.project.models.Messages;
import daredevil.project.models.Models.MessagesModel;
import daredevil.project.models.User;
import daredevil.project.repositories.base.EstatesRepository;
import daredevil.project.repositories.base.MessagesRepository;
import daredevil.project.repositories.base.UserRepository;
import daredevil.project.servieces.MessagesServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static daredevil.project.EstatesServiceTests.initiateDefaultEstates;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MessagesServiceTests {
    @Mock
    MessagesRepository messagesRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    EstatesRepository estatesRepository;

    @InjectMocks
    MessagesServiceImpl messagesService;

    List<User> users = new ArrayList<>();
    User user1=new User();
    User user2=new User();
    User user3=new User();
    User user4=new User();
    User user5=new User();
    {
        UserServiceTests.initiateDefaultUsers(user1,user2,user3,user4,user5,users);
    }

    List<Estates> estates = new ArrayList<>();
    Estates estates1=new Estates();
    Estates estates2=new Estates();
    Estates estates3=new Estates();

    {
        try {
            initiateDefaultEstates(estates1, estates2, estates3, estates);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    List<Messages> messages=new ArrayList<>();
    Messages messages1=new Messages();
    Messages messages2=new Messages();
    Messages messages3=new Messages();
    Messages messages4=new Messages();

    {
        initiateDefaultMessages(messages1, messages2, messages3, messages4, messages);
    }

    static void initiateDefaultMessages(Messages messages1, Messages messages2, Messages messages3, Messages messages4, List<Messages> messages) {
        messages1.setId(1);
        messages1.setMessageType("Text message");
        messages1.setTextMessage("Some message");
        messages1.setImageMessage(null);
        messages1.setSeen(true);
        messages1.setTimeStamp(new Date());
        messages2.setId(2);
        messages2.setMessageType("Text message");
        messages2.setTextMessage("Some message2");
        messages2.setImageMessage(null);
        messages2.setSeen(false);
        messages2.setTimeStamp(new Date());
        messages3.setId(3);
        messages3.setMessageType("Image message");
        messages3.setTextMessage("Some message");
        messages3.setImageMessage(new byte[]{1, 2, 3});
        messages3.setSeen(true);
        messages3.setTimeStamp(new Date());
        messages4.setId(4);
        messages4.setMessageType("Estate message");
        messages4.setTextMessage("Some message");
        messages4.setImageMessage(null);
        messages4.setSeen(true);
        messages4.setTimeStamp(new Date());
        messages.add(messages1);
        messages.add(messages2);
        messages.add(messages3);
        messages.add(messages4);
    }

    @Test
    public void checkForNewMessages_ShouldReturn_TrueWhen(){
        when(messagesRepository.checkForNewMessagess(user1.getId(), user2.getId())).thenReturn(true);
        boolean result=messagesService.checkForNewMessages(user1.getId(), user2.getId());
        Assert.assertTrue(result);
    }

    @Test
    public void getNewMessages_ShouldReturn_UnreadMessages(){
        List<Messages> messages=new ArrayList<>();
        messages2.setSender(user1);
        messages2.setRecipient(user2);
        messages.add(messages2);
        when(messagesRepository.getNewMessagess(user1.getId(), user2.getId())).thenReturn(messages);
        List<MessagesDTO> messagesDTOS=new ArrayList<>();
        messages2.setSeen(true);
        messagesDTOS.add(MessagesDTO.getFromMessages(messages2));
        List<MessagesDTO> result=messagesService.getNewMessages(user1.getId(), user2.getId());
        boolean resultB=true;
        for(int i=0;i<messagesDTOS.size();i++){
            if(!messagesDTOS.get(i).getSender().getUserName().equals(result.get(i).getSender().getUserName())||
           !messagesDTOS.get(i).getRecipient().getUserName().equals(result.get(i).getRecipient().getUserName())||
           !messagesDTOS.get(i).getTimeStamp().equals(result.get(i).getTimeStamp())) {
                resultB = false;
                break;
            }
        }
        Assert.assertTrue(resultB);

    }

   @Test
    public void checkForMessages_ShouldReturn_True(){
        when(messagesRepository.checkForMessages(user1.getId(), user2.getId())).thenReturn(true);
        boolean result=messagesService.checkForMessages(user1.getId(), user2.getId());
        Assert.assertTrue(result);
    }

    @Test
    public void getMessages_ShouldReturn_allMessages(){
        messages1.setSender(user1);
        messages1.setRecipient(user2);
        messages2.setSender(user1);
        messages2.setRecipient(user2);
        messages3.setSender(user1);
        messages3.setRecipient(user2);
        messages4.setSender(user1);
        messages4.setRecipient(user2);
        List<MessagesDTO> messagesDTOS=new ArrayList<>();
        for(Messages m:messages){
            messagesDTOS.add(MessagesDTO.getFromMessages(m));
        }
        when(messagesRepository.getMessagess(user1.getId(), user2.getId())).thenReturn(messages);
        List<MessagesDTO> result=messagesService.getMessages(user1.getId(), user2.getId());
        boolean resultB=true;
        for(int i=0;i<messagesDTOS.size();i++){
            if(!messagesDTOS.get(i).getSender().getUserName().equals(result.get(i).getSender().getUserName())||
                    !messagesDTOS.get(i).getRecipient().getUserName().equals(result.get(i).getRecipient().getUserName())||
                    !messagesDTOS.get(i).getTimeStamp().equals(result.get(i).getTimeStamp())) {
                resultB = false;
                break;
            }
        }
        Assert.assertTrue(resultB);

    }

    @Test
    public void postTextMessage_ShouldReturn_MessageDTO() throws CantCreateMessageException, NoUserFoundException {
        when(userRepository.getUserById(user1.getId())).thenReturn(user1);
        when(userRepository.getUserById(user2.getId())).thenReturn(user2);
        Messages message=new Messages(messages2.getTextMessage(),user1, user2, new Date(), false);
        MessagesDTO messagesDTO=MessagesDTO.getFromMessages(message);
        MessagesDTO result=messagesService.postTextMessage(messages2.getTextMessage(), user1.getId(), user2.getId());
        boolean resultB=true;
        if(!messagesDTO.getSender().getUserName().equals(result.getSender().getUserName())||
                !messagesDTO.getRecipient().getUserName().equals(result.getRecipient().getUserName())||
                !messagesDTO.getTimeStamp().equals(result.getTimeStamp()))
            resultB = false;
        Assert.assertTrue(resultB);
    }

//    @Test
//    public void postImageMessage_ShouldCall_messageRepository() throws NoUserFoundException, CantCreateMessageException, NoNewMessagesEception, NoNewMessagesEception {
//        when(userRepository.getUserById(user1.getId())).thenReturn(user1);
//        when(userRepository.getUserById(user2.getId())).thenReturn(user2);
//        MessagesModel messagesModel=new MessagesModel(user1.getId(), user2.getId(), new byte[]{1, 2 ,3 ,4});
//        when(messagesRepository.getImageMessageForSession(messagesModel.getRecipientId(), messagesModel.getSenderId())).thenReturn(messages3);
//        Messages message=new Messages(messagesModel.getImageMessage(), user1, user2, new Date(), false);
//        messagesService.postImageMessage(messagesModel);
//        verify(messagesRepository).updateMessages(messages3.getId(), message);
//    }

    @Test
    public void getEstateMessages_ShouldReturn_EstateMessasges() throws NoEstateFoundException {
        List<Messages> messages=new ArrayList<>();
        messages4.setSender(user1);
        messages4.setRecipient(user2);
        estates1.setTenant(user1);
        estates1.setLandlord(user2);
        messages.add(messages4);
        when(estatesRepository.getEstateById(estates1.getId())).thenReturn(estates1);
        when(messagesRepository.getEstateMessages(estates1.getLandlord().getId(), estates1.getTenant().getId(), estates1.getId())).thenReturn(messages);
        List<MessagesDTO> messagesDTOS=new ArrayList<>();
        messages4.setSeen(false);
        messagesDTOS.add(MessagesDTO.getFromMessages(messages4));
        List<MessagesDTO> result=messagesService.getEstateMessages(estates1.getId());
        boolean resultB=true;
        for(int i=0;i<messagesDTOS.size();i++){
            if(!messagesDTOS.get(i).getSender().getUserName().equals(result.get(i).getSender().getUserName())||
                    !messagesDTOS.get(i).getRecipient().getUserName().equals(result.get(i).getRecipient().getUserName())||
                    !messagesDTOS.get(i).getTimeStamp().equals(result.get(i).getTimeStamp())) {
                resultB = false;
                break;
            }
        }
        Assert.assertTrue(resultB);
    }

    @Test
    public void checkForEstateMessages_ShouldReturn_True() throws NoEstateFoundException {
        estates1.setLandlord(user1);
        estates1.setTenant(user2);
        when(estatesRepository.getEstateById(estates1.getId())).thenReturn(estates1);
        when(messagesRepository.checkForEstateMessages(user1.getId(), user2.getId(), estates1.getId())).thenReturn(true);
        boolean result=messagesService.checkForEstateMessages(estates1.getId());
        Assert.assertTrue(result);
    }

    @Test
    public void postEstateMessage_ShouldReturn_Message() throws NoEstateFoundException, NoUserFoundException, CantCreateMessageException {
        estates1.setLandlord(user1);
        estates1.setTenant(user2);
        when(estatesRepository.getEstateById(estates1.getId())).thenReturn(estates1);
        when(userRepository.getUserById(user2.getId())).thenReturn(user2);
        String result=messagesService.postEstateMessage("some message", estates1.getId(), user2.getId());
        Assert.assertEquals(result, " \" " + "some message" + "\" sent.");
    }
}
