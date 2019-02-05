package am.aca.quiz.software.repository;

import am.aca.quiz.software.entity.HistoryEntity;
import org.hibernate.validator.constraints.EAN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {
    List<HistoryEntity> findAllByUserEntityIdAndStatus(Long userId, String status);

    List<HistoryEntity> findAllByUserEntityId(Long userId);
}
