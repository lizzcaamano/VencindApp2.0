package com.vecindapp.repository.dao;

import com.vecindapp.entity.Categoria;
import com.vecindapp.entity.Chat;
import com.vecindapp.entity.Mensaje;
import com.vecindapp.repository.jpa.IChatJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;


public interface IChatDAO {

    Chat InsertChat(Chat chat);
    Chat findById(int Id);
    Chat findChatByRoomId(String roomId);


}
