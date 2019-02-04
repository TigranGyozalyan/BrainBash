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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


@Service
public class UserServiceImp implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final MailService mailService;


    private PasswordEncoder passwordEncoder;


    public UserServiceImp(UserRepository userRepository, MailService mailService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mailService = mailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUser(String fName, String lName, String nickname, String email, String password, String password2) throws SQLException {


        if (!password.equals(password2)) {
            throw new SQLException();
        }
        UserEntity userEntity = new UserEntity(fName, lName, email, nickname);
        userEntity.setPassword(passwordEncoder.encode(password));

        try {

            userEntity.setActivationCode(UUID.randomUUID().toString());

            userEntity.setRoles(Collections.singleton(Role.USER));

            if (!StringUtils.isEmpty(userEntity.getEmail())) {
                String message =
                        "Hello," + userEntity.getName() + "\n" +
                                "Please, visit next link: http://localhost:8080/user/activate/" +
                                userEntity.getActivationCode();

                mailService.sendText(email, "Activation", message);
            }


        } catch (MailException e) {
            throw new RuntimeException("Invalid Mail");
        }
        userRepository.saveAndFlush(userEntity);

    }

    @Override
    public List<UserEntity> getAll() throws SQLException {
        return userRepository.findAll();
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
        userRepository.save(userEntity);
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
        UserEntity userEntity = null;

        try {
            userEntity = findByEmail(s);
            return userEntity;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userEntity;
    }

    public void updateUser(UserEntity userEntity) {

        userRepository.save(userEntity);
    }

    public void updateUserPassword(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
    }


    public boolean activateUser(String code) {
        UserEntity user = userRepository.findByActivationCode(code);

        if (user == null) {
            return false;
        }

        user.setActivationCode(null);

        user.setActive(true);
        userRepository.save(user);

        return true;
    }

}
