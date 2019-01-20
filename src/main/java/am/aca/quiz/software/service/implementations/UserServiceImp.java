package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.repository.UserRepository;
import am.aca.quiz.software.entity.UserEntity;
import am.aca.quiz.software.service.dto.UserDto;
import am.aca.quiz.software.service.intefaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import java.util.Properties;


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
        return false;
    }

    @Override
    public List<UserEntity> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean update(UserEntity user, Long id) throws SQLException {
        return false;
    }

    @Override
    public boolean removeByid(Long id) throws SQLException {
        return false;
    }

    @Override
    public UserEntity getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public void sendEmail() {

        Properties properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("mail.properties");
        try {
            properties.load(inputStream);

            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });


            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("This is the Subject Line!");
            message.setContent("<h1>This is actual message</h1>", "text/html");


            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }

    }


}

