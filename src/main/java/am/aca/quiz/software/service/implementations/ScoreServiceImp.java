package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.repository.ScoreRepository;
import am.aca.quiz.software.entity.ScoreEntity;
import am.aca.quiz.software.service.dto.ScoreDto;
import am.aca.quiz.software.service.intefaces.ScoreService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
public class ScoreServiceImp implements ScoreService {


    private final ScoreRepository scoreRepository;

    public ScoreServiceImp(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }


    @Override
    public boolean addScore(double scoreValue, int count, Long topicId, Long userId) throws SQLException {
        return false;
    }

    @Override
    public List<ScoreEntity> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean update(ScoreEntity score, Long id) throws SQLException {
        return false;
    }

    @Override
    public ScoreEntity remove(ScoreEntity score) throws SQLException {
        return null;
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
