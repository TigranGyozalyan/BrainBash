package am.aca.quiz_software.repositories;

import am.aca.quiz_software.entities.ScoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<ScoreEntity,Long> {
}
