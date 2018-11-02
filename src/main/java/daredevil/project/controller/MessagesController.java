package daredevil.project.controller;

import daredevil.project.Exceptions.CantCreateMessageContentException;
import daredevil.project.Exceptions.CantCreateMessageException;
import daredevil.project.Exceptions.NoUserFountEsception;
import daredevil.project.models.DTO.MessagesDTO;
import daredevil.project.models.Messages;
import daredevil.project.models.Models.MessagesModel;
import daredevil.project.servieces.Base.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    @PostMapping("/postMessage")
    public String postMessage(@RequestBody MessagesModel messagesModel){
        String message="";
        try {
            messagesService.postMessage(messagesModel, "text");
            message="Message created.";
        } catch (CantCreateMessageException e) {
            message="Cant create message";
        } catch (CantCreateMessageContentException e) {
            message="Cant create MessageContent";
        }
        finally {
            return message;
        }
    }

    @GetMapping("/checkForNewMessages/{senderID}/{recipientID}")
    public String checkForNewMessages(@PathVariable int senderID, @PathVariable int recipientID){
        if(messagesService.checkForNewMessagess(senderID, recipientID)){
            return "true";
        }
        return "false";
    }

    @GetMapping("/getNewMessages/{senderID}/{recipientID}")
    public List<MessagesDTO> getNewMessages(@PathVariable int senderID, @PathVariable int recipientID){
        return messagesService.getNewMessagess(senderID, recipientID);
    }

    @GetMapping("/checkForMessages/{senderID}/{recipientID}")
    public String checkForMessages(@PathVariable int senderID, @PathVariable int recipientID){
        if(messagesService.checkForMessagess(senderID, recipientID)){
            return "true";
        }
        return "false";
    }

    @GetMapping("/getMessages/{senderID}/{recipientID}")
    public List<MessagesDTO> getMessages(@PathVariable int senderID, @PathVariable int recipientID){
        return messagesService.getMessagess(senderID, recipientID);
    }

    @PutMapping("/postTextMessage/{message}/{senderID}/{recipientID}")
    public MessagesDTO postTextmessage(@PathVariable String message, @PathVariable int senderID, @PathVariable int recipientID){
        try {
            return messagesService.postTextMessage(message, senderID, recipientID);
        } catch (NoUserFountEsception | CantCreateMessageException noUserFountEsception) {
            return null;
        }
    }
}
