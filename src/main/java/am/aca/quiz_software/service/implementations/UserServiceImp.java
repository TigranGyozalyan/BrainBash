package am.aca.quiz_software.service.implementations;

import am.aca.quiz_software.entity.UserEntity;
import am.aca.quiz_software.repository.UserRepository;
import am.aca.quiz_software.service.intefaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private ScoreServiceImp scoreServiceImp;
    @Autowired
    private HistoryServiceImp historyServiceImp;

    public boolean addCategory(UserEntity user) throws SQLException {
        userRepository.saveAndFlush(user);
        return true;
    }

    public List<UserEntity> getAll() throws SQLException {
        return userRepository.findAll();
    }

    @Override
    public boolean update(UserEntity user, Long id) throws SQLException {
        Optional<UserEntity> historyEntity=userRepository.findById(id);
        if(!historyEntity.isPresent()){
            throw new SQLException("Argument Not Found ");
        }
        user.setId(id);
        userRepository.saveAndFlush(user);
        return true;
    }



    public UserEntity remove(UserEntity user) throws SQLException {
        userRepository.delete(user);
        return user;
    }

    @Override
    public boolean removeByid(Long id) throws SQLException {
        return false;
    }

    @Override
    public UserEntity getById(Long id) throws SQLException {
        return null;
    }
}

