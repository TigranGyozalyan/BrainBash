package am.aca.quiz.software.repository;

import am.aca.quiz.software.entity.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {

    List<HistoryEntity> findAllByUserEntityIdAndStatus(Long userId, Enum status);

    List<HistoryEntity> findAllByUserEntityId(Long userId);

    List<HistoryEntity> findAllByStatus(Enum status);

    List<HistoryEntity> findAllByUserEntityEmail(String email);

    List<HistoryEntity> findAllByUserEntityName(String name);

    List<HistoryEntity> findAllByUserEntitySurname(String surname);

    List<HistoryEntity> findAllByUserEntityNickname(String nickname);

    @Query(value = "SELECT * FROM history WHERE user_id=?1 AND test_id=?2 AND status=?3", nativeQuery = true)
    HistoryEntity getHistoryEntityByUserIdAndTestId(Long userId, Long testId, String status);

    @Query(value = "SELECT * FROM history WHERE user_id=?1  AND status=?2", nativeQuery = true)
    HistoryEntity getHistoryByUserIdAndStatus(Long userId, String status);

    HistoryEntity findHistoryEntityBySessionId(String sessionId);
}
