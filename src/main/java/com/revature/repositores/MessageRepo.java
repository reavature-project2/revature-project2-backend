package com.revature.repositores;

import com.revature.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepo extends JpaRepository<Message, Integer> {

    @Query(value = "insert into messages (f_name, l_name, email, request_type, user_message, user_id) values (:fName, :lName, :userEmail, :typeRequest, :userMessage, :userID) Returning *",
            nativeQuery = true)
    Optional<Message> addMessage( @Param(value = "userEmail") String userEmail,
                                  @Param(value = "fName") String fName,
                                  @Param(value = "lName") String lName,
                                  @Param(value = "typeRequest") String typeRequest,
                                  @Param(value = "userMessage") String userMessage,
                                  @Param(value = "userID") int userID);

}
