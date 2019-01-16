package am.aca.quiz_software.service;

import am.aca.quiz_software.entity.ScoreEntity;
import am.aca.quiz_software.repository.ScoreRepository;
import am.aca.quiz_software.repository.TopicRepository;
import am.aca.quiz_software.repository.UserRepository;
import am.aca.quiz_software.service.dto.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class ScoreServiceImp implements ScoreService {

    @Autowired
    private  ScoreRepository scoreRepository;

    public boolean addCategory(ScoreEntity score) throws SQLException {
        return false;
    }

    public List<ScoreEntity> getAll() throws SQLException {
        return null;
    }

    public boolean update(ScoreEntity score) throws SQLException {
        return false;
    }

    public ScoreEntity remove(ScoreEntity score) throws SQLException {
        return null;
    }


}
