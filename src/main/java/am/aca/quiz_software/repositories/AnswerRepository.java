package am.aca.quiz_software.repositories;

import am.aca.quiz_software.entities.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity,Long> {
}
