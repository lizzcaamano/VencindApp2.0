package com.vecindapp.repository.dao;

import com.vecindapp.entity.Chat;
import com.vecindapp.entity.Mensaje;
import com.vecindapp.repository.jpa.IChatJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatDAO implements IChatDAO{


    @Autowired
    IChatJPA chatJPA;

    @Override
    public Chat findById(int id) {
        return chatJPA.findById(id).orElse(null);
    }

    @Override
    public Chat InsertChat(Chat chat) {
        return chatJPA.save(chat);
    }

    @Override
    public Chat findChatByRoomId(String roomId) {
        return chatJPA.findChatByRoomId(roomId);
    }
}
