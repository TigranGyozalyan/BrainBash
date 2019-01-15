package am.aca.quiz_software.service;

import am.aca.quiz_software.entity.ScoreEntity;
import am.aca.quiz_software.repository.ScoreRepository;
import am.aca.quiz_software.repository.TopicRepository;
import am.aca.quiz_software.repository.UserRepository;
import am.aca.quiz_software.service.dto.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class ScoreServiceImp implements ScoreService {

    private final ScoreRepository scoreRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;

    @Autowired
    public ScoreServiceImp(ScoreRepository scoreRepository, UserRepository userRepository, TopicRepository topicRepository) {
        this.scoreRepository = scoreRepository;
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
    }

    public void addCategory(ScoreEntity score) throws SQLException {

    }

    public List<ScoreEntity> getAll() throws SQLException {
        return null;
    }

    public void update(ScoreEntity score) throws SQLException {

    }

    public void remove(ScoreEntity score) throws SQLException {

    }
}
