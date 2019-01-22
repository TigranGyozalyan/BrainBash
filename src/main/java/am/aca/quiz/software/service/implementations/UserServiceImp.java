package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.entity.UserEntity;
import am.aca.quiz.software.repository.UserRepository;
import am.aca.quiz.software.service.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class UserServiceImp implements UserService {

    private static final String to = "yeghiazaryan99@gmail.com";
    private static final String from = "quizsoftware.noreply@gmail.com";
    private static final String password = "quizsoftware1";

    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean addUser(String fName, String lName, String nickname, String email, String password, boolean isAdmin) throws SQLException {

        UserEntity userEntity = new UserEntity(fName, lName, email, nickname, password, isAdmin);
        userRepository.saveAndFlush(userEntity);

        return true;
    }

    @Override
    public List<UserEntity> getAll() throws SQLException {
        return userRepository.findAll();
    }

    @Override
    public boolean update(UserEntity user, Long id) throws SQLException {
        UserEntity userEntity = userRepository.findById(id).get();
        if (userEntity != null) {
            user.setId(id);
            userRepository.saveAndFlush(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeByid(Long id) throws SQLException {
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public UserEntity getById(Long id) throws SQLException {
        return userRepository.findById(id).get();
    }

}

