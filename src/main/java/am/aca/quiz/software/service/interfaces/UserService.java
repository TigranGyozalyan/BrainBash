package am.aca.quiz.software.service.interfaces;


import am.aca.quiz.software.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface UserService {
    //create
    void addUser(String fName, String lName, String nickname, String email, String password, String password2) throws SQLException;

    //read
    List<UserEntity> getAll() throws SQLException;

    //update


    void removeByid(Long id) throws SQLException;

    UserEntity getById(Long id) throws SQLException;

    void removeByUserEntity(UserEntity userEntity) throws SQLException;

}
