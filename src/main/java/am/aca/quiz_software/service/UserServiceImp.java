package am.aca.quiz_software.service;

import am.aca.quiz_software.entity.UserEntity;
import am.aca.quiz_software.repository.HistoryRepository;
import am.aca.quiz_software.repository.ScoreRepository;
import am.aca.quiz_software.repository.UserRepository;
import am.aca.quiz_software.service.dto.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final HistoryRepository historyRepository;
    private final ScoreRepository scoreRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository, HistoryRepository historyRepository, ScoreRepository scoreRepository) {
        this.userRepository = userRepository;
        this.historyRepository = historyRepository;
        this.scoreRepository = scoreRepository;
    }

    public void addCategory(UserEntity user) throws SQLException {

    }

    public List<UserEntity> getAll() throws SQLException {
        return null;
    }

    public void update(UserEntity user) throws SQLException {

    }

    public void remove(UserEntity user) throws SQLException {

    }
}
