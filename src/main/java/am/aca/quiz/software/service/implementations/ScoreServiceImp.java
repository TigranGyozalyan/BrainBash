package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.entity.ScoreEntity;
import am.aca.quiz.software.entity.TopicEntity;
import am.aca.quiz.software.entity.UserEntity;
import am.aca.quiz.software.repository.ScoreRepository;
import am.aca.quiz.software.service.interfaces.ScoreService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public void addScore(Long topicId, Long userId) throws SQLException {
        TopicEntity topicEntity = topicServiceImp.getById(topicId);
        UserEntity userEntity = userServiceImp.getById(userId);

        ScoreEntity scoreEntity = new ScoreEntity();
        scoreEntity.setTopic(topicEntity);
        scoreEntity.setUserEntity(userEntity);


        scoreRepository.save(scoreEntity);

    }

    public void addScore(ScoreEntity scoreEntity) {
        scoreRepository.save(scoreEntity);
    }

    @Override
    public List<ScoreEntity> getAll() throws SQLException {
        return scoreRepository.findAll();
    }

    @Override
    public void update(ScoreEntity score) throws SQLException {
        scoreRepository.save(score);

    }


    @Override
    public ScoreEntity getById(Long id) throws SQLException {
        return scoreRepository.findById(id).get();
    }


    public List<ScoreEntity> getAllByUserId(Long id) {
        return scoreRepository.findAllByUserEntityId(id);
    }

    public void avgScore(Long testId, double userScore, Long userId) {
        List<BigInteger> topicIds = new ArrayList<>(topicServiceImp.findTopicIdByTestId(testId));

        List<Long> topicLongIds = new ArrayList<>();

        for (int i = 0; i < topicIds.size(); i++) {
            topicLongIds.add(topicIds.get(i).longValue());
        }

        for (int i = 0; i < topicLongIds.size(); i++) {

            ScoreEntity scoreEntity = new ScoreEntity();

            if (scoreRepository.findByTopicIdAndUserEntityId(topicLongIds.get(i), userId) == null) {
                try {
                    scoreEntity.setTopic(topicServiceImp.getById(topicLongIds.get(i)));
                    scoreEntity.setCount(1);
                    scoreEntity.setValue(userScore);
                    scoreEntity.setUserEntity(userServiceImp.getById(userId));
                    addScore(scoreEntity);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                scoreEntity = scoreRepository.findByTopicIdAndUserEntityId(topicLongIds.get(i), userId);
                int count = scoreEntity.getCount();
                double score = scoreRepository.findByTopicIdAndUserEntityId(topicLongIds.get(i), userId).getValue();
                scoreEntity.setCount(++count);
                double avg = (userScore + score) / count;
                scoreEntity.setValue(Math.round(avg));
                try {
                    scoreEntity.setUserEntity(userServiceImp.getById(userId));
                    scoreEntity.setTopic(topicServiceImp.getById(topicLongIds.get(i)));

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                addScore(scoreEntity);
            }
        }


    }
}
