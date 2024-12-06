package com.vecindapp.repository.jpa;

import com.vecindapp.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IChatJPA extends JpaRepository<Chat, Integer> {

    @Query("SELECT ur.role.nombre FROM UsuarioRol ur WHERE ur.usuario.id = :userId")
    List<String> findRolesByUserId(@Param("userId") Integer userId);

    Chat findChatByRoomId(String roomId);
}
