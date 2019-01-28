package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.entity.ScoreEntity;
import am.aca.quiz.software.entity.TopicEntity;
import am.aca.quiz.software.entity.UserEntity;
import am.aca.quiz.software.repository.ScoreRepository;
import am.aca.quiz.software.service.interfaces.ScoreService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class ScoreServiceImp implements ScoreService {

    private final ScoreRepository scoreRepository;
    private final TopicServiceImp topicServiceImp;
    private final UserServiceImp userServiceImp;

    public ScoreServiceImp(ScoreRepository scoreRepository, TopicServiceImp topicServiceImp, UserServiceImp userServiceImp) {
        this.scoreRepository = scoreRepository;
        this.topicServiceImp = topicServiceImp;
        this.userServiceImp = userServiceImp;
    }


    @Override
    public void addScore(Long topicId,Long userId) throws SQLException {
        TopicEntity topicEntity=topicServiceImp.getById(topicId);
        UserEntity userEntity=userServiceImp.getById(userId);

        ScoreEntity scoreEntity=new ScoreEntity();
        scoreEntity.setTopic(topicEntity);
        scoreEntity.setUserEntity(userEntity);


        scoreRepository.saveAndFlush(scoreEntity);

    }

    @Override
    public List<ScoreEntity> getAll() throws SQLException {
        return scoreRepository.findAll();
    }

    @Override
    public void update(ScoreEntity score, Long id) throws SQLException {
        ScoreEntity scoreEntity = scoreRepository.findById(id).get();
        if (scoreEntity != null) {
            score.setId(id);
            scoreRepository.saveAndFlush(score);
        }
    }


    @Override
    public ScoreEntity getById(Long id) throws SQLException {
        return scoreRepository.findById(id).get();
    }

    @Override
    public void removeById(Long id) throws SQLException {
        scoreRepository.deleteById(id);
    }

    @Override
    public int getTestCountByTopic(Long user_id) {
        return 0;
    }
}
