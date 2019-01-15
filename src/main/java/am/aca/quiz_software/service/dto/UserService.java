package am.aca.quiz_software.service.dto;

<<<<<<< HEAD
import am.aca.quiz_software.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface UserService {
    //create
    void addCategory(UserEntity user) throws SQLException;

    //read
    List<UserEntity> getAll() throws SQLException;

    //update
    void update(UserEntity user) throws SQLException;

    //delete
    void remove(UserEntity user) throws SQLException;
=======
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    //TODO methods and constructor
>>>>>>> dfa5e88c635184eb52a4efb6a23550b85acf9daa
}
