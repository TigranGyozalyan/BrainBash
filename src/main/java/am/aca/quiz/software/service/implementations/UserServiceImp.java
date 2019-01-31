package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.entity.UserEntity;
import am.aca.quiz.software.repository.UserRepository;
import am.aca.quiz.software.service.MailService;
import am.aca.quiz.software.service.interfaces.UserService;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final MailService mailService;


    public UserServiceImp(UserRepository userRepository, MailService mailService) {
        this.userRepository = userRepository;
        this.mailService = mailService;
    }

    @Override
    public void addUser(String fName, String lName, String nickname, String email, String password,String password2) throws SQLException {


        if(!password.equals(password2)){
            throw new SQLException();
        }
        UserEntity userEntity = new UserEntity(fName, lName, email, nickname, password,"user");
        try {
            mailService.sendHtml(email,"Confirmation","mailConfirmation");
        }catch (MailException e){
           throw new RuntimeException("Invalid Mail");
        }
        userRepository.saveAndFlush(userEntity);

    }

    @Override
    public List<UserEntity> getAll() throws SQLException {
        return userRepository.findAll();
    }

    @Override
    public void update(UserEntity user, Long id) throws SQLException {
        UserEntity userEntity = userRepository.findById(id).get();
        if (userEntity != null) {
            user.setId(id);
            userRepository.saveAndFlush(user);
        }

    }

    @Override
    public void removeByid(Long id) throws SQLException {
        userRepository.deleteById(id);
    }

    @Override
    public UserEntity getById(Long id) throws SQLException {
        return userRepository.findById(id).get();
    }

}
