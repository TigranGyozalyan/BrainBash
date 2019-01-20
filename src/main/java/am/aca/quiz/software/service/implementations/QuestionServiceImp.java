package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.repository.QuestionRepository;
import am.aca.quiz.software.service.dto.QuestionDto;
import am.aca.quiz.software.service.intefaces.QuestionService;
import am.aca.quiz.software.entity.QuestionEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
public class QuestionServiceImp implements QuestionService {


    private final QuestionRepository questionRepository;

    public QuestionServiceImp(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    @Override
    public boolean addQuestion(String question, Enum level, int correctAnswerCount, double points, Long topicCentityId) throws SQLException {
        return false;
    }

    @Override
    public List<QuestionEntity> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean update(QuestionEntity question, Long id) throws SQLException {
        return false;
    }



    @Override
    public QuestionEntity getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public boolean removeByid(Long id) throws SQLException {
        return false;
    }
}
