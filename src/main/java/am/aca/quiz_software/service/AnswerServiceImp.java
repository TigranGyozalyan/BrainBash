package am.aca.quiz_software.service;

import am.aca.quiz_software.entity.AnswerEntity;
import am.aca.quiz_software.repository.AnswerRepository;
import am.aca.quiz_software.service.dto.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public  class AnswerServiceImp implements AnswerService {

    @Autowired
    private  AnswerRepository answerRepository;

    public boolean addCategory(AnswerEntity answer) throws SQLException {
            answerRepository.saveAndFlush(answer);
            return true;
    }

    public List<AnswerEntity> getAll() throws SQLException {
        return answerRepository.findAll();
    }

    @Override
    public boolean update(AnswerEntity answer, Long id) throws SQLException {

        Optional<AnswerEntity>answerEntity=answerRepository.findById(id);
        if(!answerEntity.isPresent()){
            throw new SQLException("Argument Not Found ");
        }

        answer.setId(id);
        answerRepository.saveAndFlush(answer);
        return true;
    }


    public boolean update(AnswerEntity answer) throws SQLException {
        return false;
    }

    public AnswerEntity remove(AnswerEntity answer) throws SQLException {
        answerRepository.delete(answer);
        return answer;
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
