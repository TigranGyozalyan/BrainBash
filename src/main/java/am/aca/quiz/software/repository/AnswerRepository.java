package am.aca.quiz.software.repository;

import am.aca.quiz.software.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity,Long> {
    @Query(value = "SELECT question_id from answer where answer=?1",nativeQuery = true)
    Long  findQuestionIdByAnswer(String answer);

    AnswerEntity findAnswerEntitiesByAnswer(String answer);

    @Query(value = "SELECT * FROM answer WHERE question_id = ?1",nativeQuery = true)
    List<AnswerEntity> findAnswersByQuestionId(Long questionId);
}
