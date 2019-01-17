package am.aca.quiz.software.repository;

import am.aca.quiz.software.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<TestEntity,Long> {
}
