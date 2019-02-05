package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.entity.AnswerEntity;
import am.aca.quiz.software.entity.QuestionEntity;
import am.aca.quiz.software.repository.AnswerRepository;
import am.aca.quiz.software.service.interfaces.AnswerService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImp implements AnswerService {


    private final AnswerRepository answerRepository;
    private final QuestionServiceImp questionServiceImp;

    public AnswerServiceImp(AnswerRepository answerRepository, QuestionServiceImp questionServiceImp) {
        this.answerRepository = answerRepository;
        this.questionServiceImp = questionServiceImp;
    }


    @Override
    public void addAnswer(String answer, String description, boolean isCorrect, Long questionId) throws SQLException {

        QuestionEntity questionEntity = questionServiceImp.getById(questionId);
        AnswerEntity answerEntity = new AnswerEntity(answer, description, isCorrect, questionEntity);
        questionEntity.getAnswerEntities().add(answerEntity);

        answerRepository.saveAndFlush(answerEntity);

    }

    @Override
    public List<AnswerEntity> getAll() throws SQLException {
        return answerRepository.findAll();
    }


    @Override
    public void update(AnswerEntity updateAnswer) throws SQLException {
        answerRepository.save(updateAnswer);
    }

    @Override
    public AnswerEntity getById(Long id) throws SQLException {
        Optional<AnswerEntity> answerEntity = answerRepository.findById(id);
        if (!answerEntity.isPresent()) {
            throw new SQLException("Entity Not Found");
        }
        return answerEntity.get();
    }

    @Override
    public void removeById(Long id) throws SQLException {
        AnswerEntity deleted_answer = answerRepository.findById(id).get();
        answerRepository.delete(deleted_answer);
    }

    @Override
    public List<AnswerEntity> getAnswerEntitiesByQuestionId(Long questionId) {
        return answerRepository.findAnswersByQuestionId(questionId);
    }

}
