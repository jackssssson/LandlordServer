package daredevil.project.controller;

import daredevil.project.Exceptions.CantCreateMessageContentException;
import daredevil.project.Exceptions.CantCreateMessageException;
import daredevil.project.models.Models.MessagesModel;
import daredevil.project.servieces.Base.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

//    @GetMapping("checkForNewMessagess/{id}")
//    public String checkForNewMessagess(@PathVariable int id){
//
//    }
}
