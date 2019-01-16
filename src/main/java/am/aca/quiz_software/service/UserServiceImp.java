package am.aca.quiz_software.service;

import am.aca.quiz_software.entity.UserEntity;
import am.aca.quiz_software.repository.HistoryRepository;
import am.aca.quiz_software.repository.ScoreRepository;
import am.aca.quiz_software.repository.UserRepository;
import am.aca.quiz_software.service.dto.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    private  UserRepository userRepository;

    public boolean addCategory(UserEntity user) throws SQLException {
        userRepository.saveAndFlush(user);
        return true;
    }

    public List<UserEntity> getAll() throws SQLException {
        return userRepository.findAll();
    }

    public boolean update(UserEntity user) throws SQLException {
        //toDO
        return false;
    }

    public UserEntity remove(UserEntity user) throws SQLException {
        userRepository.delete(user);
        return user;
    }
}
