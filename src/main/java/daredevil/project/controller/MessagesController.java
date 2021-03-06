package daredevil.project.controller;

import daredevil.project.Exceptions.CantCreateMessageException;
import daredevil.project.Exceptions.NoEstateFoundException;
import daredevil.project.Exceptions.NoNewMessagesEception;
import daredevil.project.Exceptions.NoUserFoundException;
import daredevil.project.models.DTO.MessagesDTO;
import daredevil.project.models.Models.MessagesModel;
import daredevil.project.servieces.Base.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessagesController {
    private MessagesService messagesService;

    @Autowired
    public MessagesController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @GetMapping("/checkForNewMessages/{senderID}/{recipientID}")
    public String checkForNewMessages(@PathVariable int senderID,
                                      @PathVariable int recipientID){
        if(messagesService.checkForNewMessages(senderID, recipientID)){
            return "true";
        }
        return "false";
    }

    @GetMapping("/getNewMessages/{senderID}/{recipientID}")
    public List<MessagesDTO> getNewMessages(@PathVariable int senderID,
                                            @PathVariable int recipientID){
        return messagesService.getNewMessages(senderID, recipientID);
    }

    @GetMapping("/checkForMessages/{senderID}/{recipientID}")
    public String checkForMessages(@PathVariable int senderID,
                                   @PathVariable int recipientID){
        if(messagesService.checkForMessages(senderID, recipientID)){
            return "true";
        }
        return "false";
    }

    @GetMapping("/getMessages/{senderID}/{recipientID}")
    public List<MessagesDTO> getMessages(@PathVariable int senderID,
                                         @PathVariable int recipientID){
        return messagesService.getMessages(senderID, recipientID);
    }

    @PutMapping("/postTextMessage/{message}/{senderID}/{recipientID}")
    public MessagesDTO postTextmessage(@PathVariable String message,
                                       @PathVariable int senderID,
                                       @PathVariable int recipientID){
        try {
            return messagesService.postTextMessage(message, senderID, recipientID);
        } catch (NoUserFoundException | CantCreateMessageException noUserFoundException) {
            return null;
        }
    }

    @PostMapping("/postImage")
    public void postImage(@RequestBody MessagesModel messagesModel){
        try {
            messagesService.postImageMessage(messagesModel);
        } catch (NoUserFoundException |
                CantCreateMessageException |
                NoNewMessagesEception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/checkForEstateMessages/{id}")
    public String checkForEstateMessages(@PathVariable int id){
        try {
             if(messagesService.checkForEstateMessages(id))
                 return "true";
             return "false";
        } catch (NoEstateFoundException e) {
            return "No Estate Found";
        }
    }

    @GetMapping("/getEstateMessages/{estateID}")
    public List<MessagesDTO> getEstateMessages(@PathVariable int estateID) throws NoEstateFoundException {
        return messagesService.getEstateMessages(estateID);
    }

    @PutMapping("/postEstateMessage/{message}/{estateID}/{senderID}")
    public String postEstateMessage(@PathVariable String message,
                                    @PathVariable int estateID,
                                    @PathVariable int senderID){
        try {
            return messagesService.postEstateMessage(message, estateID, senderID);
        } catch (NoEstateFoundException e) {
            return "No estate found";
        } catch (NoUserFoundException e) {
            return "No user found";
        } catch (CantCreateMessageException e) {
            return "Can't create message";
        }
    }
}
