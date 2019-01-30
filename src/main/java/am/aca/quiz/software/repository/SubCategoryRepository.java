package am.aca.quiz.software.repository;

import am.aca.quiz.software.entity.CategoryEntity;
import am.aca.quiz.software.entity.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity, Long> {
    SubCategoryEntity findSubCategoryEntitiesByTypeName(String typeName);

    //    @Query(value = "select SubCategoryEntity.category FROM SubCategoryEntity where SubCategoryEntity .typeName = ?1")
    // CategoryEntity findCategoryIdBySubCategoryTypeName(String typename);
    @Query(value = "SELECT category_id FROM sub_category WHERE type_name=?1", nativeQuery = true)
    Long findCategoryIdBySubCategoryTypeName(String typename);
}
