package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.entity.UserEntity;
import am.aca.quiz.software.entity.enums.Role;
import am.aca.quiz.software.repository.UserRepository;
import am.aca.quiz.software.service.MailService;
import am.aca.quiz.software.service.interfaces.UserService;
import org.springframework.mail.MailException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;


@Service
public class UserServiceImp implements UserService, UserDetailsService {

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
        UserEntity userEntity = new UserEntity(fName, lName, email, true, nickname, password);
      userEntity.getRoles().add(Role.USER);
      userEntity.getRoles().add(Role.ADMIN);

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


    @Override
    public void removeByUserEntity(UserEntity userEntity) throws SQLException {
        userEntity.setActive(false);
    }

    public UserEntity findByEmail(String email) throws SQLException {
        UserEntity userEntity = null;
        if (userRepository.findByEmail(email).isPresent()) {
            userEntity = userRepository.findByEmail(email).get();
        }

        return userEntity;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity=null;

        try {
            userEntity=findByEmail(s);
            return  userEntity;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userEntity;
    }
}
