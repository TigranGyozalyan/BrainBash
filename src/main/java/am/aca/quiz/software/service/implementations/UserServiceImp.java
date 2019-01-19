package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.repository.UserRepository;
import am.aca.quiz.software.entity.UserEntity;
import am.aca.quiz.software.service.dto.UserDto;
import am.aca.quiz.software.service.intefaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

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
}

