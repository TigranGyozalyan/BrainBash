package am.aca.quiz.software.repository;

import am.aca.quiz.software.entity.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<ScoreEntity, Long> {

    List<ScoreEntity> findAllByUserEntityId(Long id);

    ScoreEntity findByTopicIdAndUserEntityId(Long topicId, Long userId);
}
