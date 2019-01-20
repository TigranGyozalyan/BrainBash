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


    public boolean addScore(ScoreEntity score) throws SQLException {
        scoreRepository.saveAndFlush(score);
        return true;
    }

    public List<ScoreDto> getAll() throws SQLException {
        return ScoreDto.mapEntityToDtos(scoreRepository.findAll());
    }

    @Override
    public boolean update(ScoreEntity score, Long id) throws SQLException {
        ScoreEntity updated_score =scoreRepository.findById(id).get();
        if (updated_score != null) {
            score.setId(id);
            return addScore(score);
        }
        return false;
    }

    public ScoreDto remove(ScoreEntity score) throws SQLException {
        scoreRepository.delete(score);
        return ScoreDto.mapEntityToDto(score);
    }

    @Override
    public ScoreDto getByid(Long id) throws SQLException {
        Optional<ScoreEntity> scoreEntity = scoreRepository.findById(id);
        if (!scoreEntity.isPresent()) {
            throw new SQLException("Entity not found");
        }
        return ScoreDto.mapEntityToDto(scoreEntity.get());
    }

    @Override
    public boolean removeByid(Long id) throws SQLException {
        ScoreEntity deleted_score =scoreRepository.findById(id).get();
        scoreRepository.delete(deleted_score);
        return true;
    }
}
