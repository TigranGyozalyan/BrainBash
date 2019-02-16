package am.aca.quiz.software.repository;

import am.aca.quiz.software.entity.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity, Long> {

    SubCategoryEntity findSubCategoryEntitiesByTypeName(String typeName);
}
