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

    public boolean addAnswer(AnswerEntity answer) throws SQLException {
        answerRepository.saveAndFlush(answer);
        return true;
    }

    public List<AnswerEntity> getAll() throws SQLException {
        return answerRepository.findAll();
    }


    @Override
    public boolean update(AnswerEntity answer, Long id) throws SQLException {
        AnswerEntity updated_answer = answerRepository.findById(id).get();
        if (updated_answer != null) {
            answer.setId(id);
            return addAnswer(answer);
        }
        return false;
    }

    @Override
    public AnswerDto remove(AnswerEntity answer, Long id) throws SQLException {
        answerRepository.delete(answer);
        return AnswerDto.mapEntityToDto(answer);
    }



    @Override
    public AnswerDto getById(Long id) throws SQLException {
        Optional<AnswerEntity> answerEntity = answerRepository.findById(id);
        if (!answerEntity.isPresent()) {
            throw new SQLException("Entity Not Found");
        }
        return AnswerDto.mapEntityToDto(answerEntity.get());
    }

    @Override
    public boolean removeById(Long id) throws SQLException {
        AnswerEntity deleted_answer = answerRepository.findById(id).get();
        answerRepository.delete(deleted_answer);
        return true;
    }


}
