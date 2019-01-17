package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.repository.ScoreRepository;
import am.aca.quiz.software.entity.ScoreEntity;
import am.aca.quiz.software.service.intefaces.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
public class ScoreServiceImp implements ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private TopicServiceImp topicServiceImp;

    public boolean addCategory(ScoreEntity score) throws SQLException {
        scoreRepository.saveAndFlush(score);
        return true;
    }

    public List<ScoreEntity> getAll() throws SQLException {
        return scoreRepository.findAll();
    }

    @Override
    public boolean update(ScoreEntity score, Long id) throws SQLException {
        Optional<ScoreEntity> historyEntity = scoreRepository.findById(id);
        if (!historyEntity.isPresent()) {
            throw new SQLException("Argument Not Found ");
        }
        score.setId(id);
        scoreRepository.saveAndFlush(score);
        return true;
    }

    public ScoreEntity remove(ScoreEntity score) throws SQLException {
        scoreRepository.delete(score);
        return score;
    }

    @Override
    public ScoreEntity getByid(Long id) throws SQLException {
        return null;
    }

    @Override
    public boolean removeByid(Long id) throws SQLException {
        return false;
    }
}
