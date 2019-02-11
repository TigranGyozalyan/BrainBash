package am.aca.quiz.software.repository;

import am.aca.quiz.software.entity.TopicEntity;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Set;

@Repository
public interface TopicRepository extends JpaRepository<TopicEntity, Long> {

    TopicEntity findByTopicName(String topicName);


    @Query(value = "SELECT t.id  FROM topic as t  INNER JOIN questions as q  ON t.id=q.topic_entity_id INNER JOIN question_test as qt ON qt.question_id=q.id WHERE qt.test_id=?1",nativeQuery = true)
    Set<BigInteger> findTopicByTestId(Long testId);
}
