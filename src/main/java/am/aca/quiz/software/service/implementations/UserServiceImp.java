package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.entity.UserEntity;
import am.aca.quiz.software.entity.enums.Role;
import am.aca.quiz.software.repository.UserRepository;
import am.aca.quiz.software.service.MailService;
import am.aca.quiz.software.service.interfaces.UserService;
import org.hibernate.hql.internal.ast.SqlASTFactory;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;


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
            return;
        }

        UserEntity userEntity = new UserEntity(fName, lName, email, nickname);
        userEntity.setPassword(passwordEncoder.encode(password));

        try {

            userEntity.setActivationCode(UUID.randomUUID().toString());

            userEntity.setRoles(Collections.singleton(Role.USER));

            if (!StringUtils.isEmpty(userEntity.getEmail())) {
                String message =
                        "Hello," + userEntity.getName() + "\n" +
                                "Please, visit the following link: http://localhost:8080/user/activate/" +
                                userEntity.getActivationCode();
                mailService.sendText(email, "Activation", message);
            }
        } catch (MailException e) {
            throw new RuntimeException("Invalid Mail");
        }
        userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> getAll() throws SQLException {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void removeById(Long id) throws SQLException {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new SQLException();
        }
    }

    @Override
    public UserEntity getById(Long id) throws SQLException {
        if (userRepository.findById(id).isPresent()) {
            return userRepository.findById(id).get();
        }
        throw new SQLException();
    }


    @Transactional
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
            return userEntity;
        }
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

    public List<UserEntity> findByNameLike(String name) {
        return userRepository.findByNameLike(name);
    }

    public List<UserEntity> findBySurnameLike(String surname) {
        return userRepository.findBySurnameLike(surname);
    }

    public List<UserEntity> findByNicknameLike(String nickname) {
        return userRepository.findByNickNameLike(nickname);
    }
}
