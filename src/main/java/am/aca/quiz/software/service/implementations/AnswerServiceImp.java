package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.entity.AnswerEntity;
import am.aca.quiz.software.repository.AnswerRepository;
import am.aca.quiz.software.service.dto.AnswerDto;
import am.aca.quiz.software.service.intefaces.AnswerService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImp implements AnswerService {


    private final AnswerRepository answerRepository;

    public AnswerServiceImp(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }


    @Override
    public boolean addAnswer(String answer, String description, boolean isCorrect, Long questionId) throws SQLException {
        return false;
    }

    @Override
    public List<AnswerEntity> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean update(AnswerEntity answer, Long id) throws SQLException {
        return false;
    }

    @Override
    public AnswerEntity remove(AnswerEntity answer, Long id) throws SQLException {
        return null;
    }

    @Override
    public AnswerEntity getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public boolean removeById(Long id) throws SQLException {
        return false;
    }


}
