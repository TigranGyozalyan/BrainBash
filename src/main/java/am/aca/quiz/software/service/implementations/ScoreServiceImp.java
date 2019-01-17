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

    public boolean addScore(ScoreEntity score) throws SQLException {
        scoreRepository.saveAndFlush(score);
        return true;
    }

    public List<ScoreEntity> getAll() throws SQLException {
        return scoreRepository.findAll();
    }

    @Override
    public boolean update(ScoreEntity score, Long id) throws SQLException {
        ScoreEntity updated_score = getByid(id);
        if (updated_score != null) {
            score.setId(id);
            return addScore(score);
        }
        return false;
    }

    public ScoreEntity remove(ScoreEntity score) throws SQLException {
        scoreRepository.delete(score);
        return score;
    }

    @Override
    public ScoreEntity getByid(Long id) throws SQLException {
        Optional<ScoreEntity> scoreEntity = scoreRepository.findById(id);
        if (!scoreEntity.isPresent()) {
            throw new SQLException("Entity not found");
        }
        return scoreEntity.get();
    }

    @Override
    public boolean removeByid(Long id) throws SQLException {
        ScoreEntity deleted_score = getByid(id);
        scoreRepository.delete(deleted_score);
        return true;
    }
}
