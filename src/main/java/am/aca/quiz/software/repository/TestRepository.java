package am.aca.quiz.software.repository;

import am.aca.quiz.software.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<TestEntity,Long> {
    TestEntity findTestEntitiesByTest(String test);

    @Query(value = " SELECT t.id FROM test as t INNER JOIN question_test as qt ON t.id=qt.test_id INNER JOIN questions as q ON qt.question_id=q.id WHERE q.topic_entity_id=?1",nativeQuery = true)
    List<BigInteger> findTestByTopicId(Long id);

}
