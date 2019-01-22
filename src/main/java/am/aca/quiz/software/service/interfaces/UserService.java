package am.aca.quiz.software.service.interfaces;


import am.aca.quiz.software.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface UserService {
    //create
    boolean addUser(String fName, String lName, String nickname, String email, String password, boolean isAdmin) throws SQLException;

    //read
    List<UserEntity> getAll() throws SQLException;

    //update
    boolean update(UserEntity user, Long id) throws SQLException;


    boolean removeByid(Long id) throws SQLException;

    UserEntity getById(Long id) throws SQLException;

}
