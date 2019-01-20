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


    public boolean addQuestion(QuestionEntity question) throws SQLException {
        questionRepository.saveAndFlush(question);
        return true;
    }

    public List<QuestionDto> getAll() throws SQLException {
        return QuestionDto.mapEntityToDtos(questionRepository.findAll());
    }

    @Override
    public boolean update(QuestionEntity question, Long id) throws SQLException {

        QuestionEntity updatedQuestion = questionRepository.findById(id).get();
        if (updatedQuestion != null) {
            question.setId(id);
            return addQuestion(question);
        }
        return false;
    }

    public QuestionDto remove(QuestionEntity question) throws SQLException {
        questionRepository.delete(question);
        return QuestionDto.mapEntityToDto(question);
    }

    @Override
    public QuestionDto getById(Long id) throws SQLException {
        Optional<QuestionEntity> questionEntity = questionRepository.findById(id);
        if (!questionEntity.isPresent()) {
            throw new SQLException("Entity not found");
        }
        return QuestionDto.mapEntityToDto(questionEntity.get());
    }

    @Override
    public boolean removeByid(Long id) throws SQLException {
        QuestionEntity deleted_question = questionRepository.findById(id).get();
        questionRepository.delete(deleted_question);
        return true;
    }
}
