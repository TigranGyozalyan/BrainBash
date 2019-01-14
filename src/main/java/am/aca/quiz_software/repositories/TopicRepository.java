package am.aca.quiz_software.repositories;

import am.aca.quiz_software.entities.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<TopicEntity,Long> {
}
