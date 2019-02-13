package am.aca.quiz.software.repository;

import am.aca.quiz.software.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);


    UserEntity findByActivationCode(String activationCode);

        @Query(value = "SELECT * FROM users where first_name ILIKE ?1",nativeQuery = true)
    List<UserEntity> findByNameLike(String name);

    @Query(value = "SELECT * FROM users where last_name ILIKE ?1", nativeQuery = true)
    List<UserEntity> findBySurnameLike(String surname);

    //TODO ASK  ABOUT INDEX
    @Query(value = "SELECT * FROM users where nickname ILIKE ?1", nativeQuery = true)
    List<UserEntity> findByNickNameLike(String Nickname);

}

