package am.aca.quiz_software.repository;

import am.aca.quiz_software.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity,Long> {
}
