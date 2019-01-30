package am.aca.quiz.software.repository;

import am.aca.quiz.software.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {

    CategoryEntity findCategoryEntityByType(String type);
    CategoryEntity findCategoryEntityById(Long id);
    CategoryEntity removeById(Long id);


}
