package am.aca.quiz_software.repositories;

import am.aca.quiz_software.entities.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity,Long> {

}
