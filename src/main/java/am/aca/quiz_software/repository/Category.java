package am.aca.quiz_software.repository;

import am.aca.quiz_software.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Category extends JpaRepository<CategoryEntity,Long> {
}
