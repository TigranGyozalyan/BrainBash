package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.entity.AnswerEntity;
import am.aca.quiz.software.repository.AnswerRepository;
import am.aca.quiz.software.service.intefaces.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImp implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionServiceImp questionServiceImp;

    public boolean addAnswer(AnswerEntity answer) throws SQLException {
        answerRepository.saveAndFlush(answer);
        return true;
    }

    public List<AnswerEntity> getAll() throws SQLException {
        return answerRepository.findAll();
    }

    @Override
    public boolean update(AnswerEntity answer, Long id) throws SQLException {
        AnswerEntity updated_answer = getById(id);
        if (updated_answer != null) {
            answer.setId(id);
            return addAnswer(answer);
        }
        return false;
    }

    public AnswerEntity remove(AnswerEntity answer) throws SQLException {
        answerRepository.delete(answer);
        return answer;
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
    public boolean removeById(Long id) throws SQLException {
        AnswerEntity deleted_answer = getById(id);
        answerRepository.delete(deleted_answer);
        return true;
    }


}
