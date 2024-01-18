package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.service.AccountService;
import com.example.entity.Message;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    AccountService accountService;
    MessageService messageService;

    @Autowired
    SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }


    @PostMapping("register")
    public Account registerNewAcc(@RequestBody Account newAcc){
        return accountService.insertAccount(newAcc);
    }

    @PostMapping("login")
    public Account loginAcc(@RequestBody Account login){
        return accountService.loginAccount(login);
    }

    @PostMapping("messages")
    public Message postMessage(@RequestBody Message mess){
        return messageService.postNewMessage(mess);
    }

    @GetMapping("messages")
    public List<Message> getAllMessages(){
        return messageService.getAllMessages();
    }

    @GetMapping("messages/{message_id}")
    public Message getMessageById(@PathVariable Integer message_id){
        return messageService.getMessageById(message_id);

    }

    @DeleteMapping("messages/{message_id}")
    public String deleteMessageById(@PathVariable Integer message_id){
        return messageService.deleteMessageById(message_id);
    }

    @PatchMapping("messages/{message_id}")
    public String updateMessageById(@RequestBody Message updMessage, @PathVariable Integer message_id){
        return messageService.updateMessageById(updMessage.getMessage_text(), message_id);
    }

    @GetMapping("accounts/{account_id}/messages")
    public List<Message> getMessagesByPosterID(@PathVariable Integer account_id){
        return messageService.getMessagesByPosterID(account_id);
    }
}
