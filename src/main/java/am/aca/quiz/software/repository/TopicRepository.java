package am.aca.quiz.software.repository;

import am.aca.quiz.software.entity.TopicEntity;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<TopicEntity, Long> {
    TopicEntity findByTopicName(String topicName);

    @Query(value = "SELECT subcategory_id FROM topic WHERE topic_name =?1", nativeQuery = true)
    Long findSubCategoryIdByTopicName(String typename);
}
