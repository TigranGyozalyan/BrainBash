package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.repository.UserRepository;
import am.aca.quiz.software.entity.UserEntity;
import am.aca.quiz.software.service.dto.UserDto;
import am.aca.quiz.software.service.intefaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
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

    private static final String to=;
    private static final String from=;
    private static final String password=;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ScoreServiceImp scoreServiceImp;
    @Autowired
    private HistoryServiceImp historyServiceImp;

    public boolean addUser(UserEntity user) throws SQLException {
        userRepository.saveAndFlush(user);
        return true;
    }

    public List<UserDto> getAll() throws SQLException {
        return UserDto.mapEntitiesToDto(userRepository.findAll());
    }

    @Override
    public boolean update(UserEntity user, Long id) throws SQLException {
        UserEntity updated_user = userRepository.findById(id).get();
        if (updated_user != null) {
            user.setId(id);
            return addUser(user);
        }
        return false;
    }



    public UserDto remove(UserEntity user) throws SQLException {
        userRepository.delete(user);
        return UserDto.mapEntityToDto(user);
    }

    @Override
    public boolean removeByid(Long id) throws SQLException {
        UserEntity deleted_user = userRepository.findById(id).get();
        userRepository.delete(deleted_user);
        return true;
    }

    @Override
    public UserDto getById(Long id) throws SQLException {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if(!userEntity.isPresent()) {
            throw new SQLException("Entity not found");
        }
        return UserDto.mapEntityToDto(userEntity.get());
    }

    @Override
    public void sendEmail() {

        Properties properties=new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("mail.properties");
        try {
            properties.load(inputStream);
            /*properties.put("mail.smtp.auth","true");
            properties.put("mail.smtp.starttls.enable","true");
            properties.put("mail.smtp.host","smtp.gmail.com");
            properties.put("mail.smtp.port",587);*/
            Session session=Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from,password);
                }
            });


            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("This is the Subject Line!");
            message.setContent("<h1>This is actual message</h1>", "text/html");

          /*  Transport transport=session.getTransport();
            transport.connect(null,properties.getProperty("mail.smtps.password"));
            transport.sendMessage(message,message.getAllRecipients());
            transport.close();*/
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }

    }


}

