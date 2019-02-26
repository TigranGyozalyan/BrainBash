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
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


@Service
public class UserServiceImp implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final MailService mailService;

    private PasswordEncoder passwordEncoder;

    private String activate;


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
        userEntity.setRoles(Collections.singleton(Role.USER));
        addToDb(userEntity, password, email);
    }

    @Transactional
    public void ubdateNonActiveUser(UserEntity userEntity) {
        addToDb(userEntity, userEntity.getPassword(), userEntity.getEmail());
    }

    @Transactional
    protected void addToDb(UserEntity userEntity, String password, String email) {
        userEntity.setPassword(passwordEncoder.encode(password));
        userEntity.setActivationCode(UUID.randomUUID().toString());
        try {

            new Thread(() -> mailService.sendActivationCode(email, userEntity)).start();

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

    public UserEntity findByActiovationCode(String code) {
        return userRepository.findByActivationCode(code);
    }

    public boolean activateUser(String code) {

        activate = code;

        UserEntity user = findByActiovationCode(code);

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

    public String getCode() {
        return activate;
    }

    public void setCode(String code) {
        this.activate = code;
    }

    public List<Long> findAdminsIfExist(){
        return userRepository.findAdminIfExists();
    }


}
