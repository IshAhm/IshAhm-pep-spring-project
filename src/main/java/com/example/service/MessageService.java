package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

import java.util.List;
import java.util.Optional;

import javassist.bytecode.ExceptionTable;

@Component
public class MessageService {
    MessageRepository messageRepository;
    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }
    
    public Message postNewMessage(Message mess){
        if(mess.getMessage_text().length() > 255 || mess.getMessage_text().equals("")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } 
        try{
            Message message = messageRepository.save(mess);
            return message;
        }    
        catch(Exception DataIntegrityViolationException){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message getMessageById(Integer messID){
        return messageRepository.findByMessageID(messID);
    }

    public String deleteMessageById(Integer messID){
        Message delMess = messageRepository.findByMessageID(messID);
        messageRepository.deleteMessageById(messID);
        if(delMess != null){
            return "1";
        } else{
            return null;
        }
    }

    public String updateMessageById(String updatedText, Integer messID){
        if(updatedText.length() > 255 || updatedText.equals("")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Message existingMessage = messageRepository.findByMessageID(messID);
        if(existingMessage == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        existingMessage.setMessage_text(updatedText);
        messageRepository.save(existingMessage);
        return "1";
    }

    public List<Message> getMessagesByPosterID(Integer posterID){
        return messageRepository.findAllMessagesByAccID(posterID);
    }
}