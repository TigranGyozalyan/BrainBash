package am.aca.quiz.software.repository;

import am.aca.quiz.software.entity.QuestionEntity;
import am.aca.quiz.software.entity.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity,Long> {
    List<QuestionEntity> findQuestionEntitiesByTopicEntity(TopicEntity topicEntity);

    QuestionEntity findQuestionEntitiesByQuestion(String question);


}
