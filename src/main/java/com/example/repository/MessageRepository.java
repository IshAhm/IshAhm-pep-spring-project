package com.example.repository;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Message;


public interface MessageRepository extends JpaRepository<Message, Long> {
    
    @Query(value = "Select * FROM message WHERE message_id =:messageId", nativeQuery = true)
    public Message findByMessageID(@Param("messageId") Integer messID);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM message WHERE message_id =:messageId", nativeQuery = true)
    public void deleteMessageById(@Param("messageId") Integer messID);

    @Transactional
    @Modifying
    @Query(value = "UPDATE message SET message_text =:newText WHERE message_id =:messageId", nativeQuery = true)
    public void updateMessageById(@Param("newText") String updatedText, @Param("messageId") Integer messID);

    @Query(value = "Select * FROM message WHERE posted_by =:accID", nativeQuery = true)
    public List<Message> findAllMessagesByAccID(@Param("accID") Integer posterID);
}
