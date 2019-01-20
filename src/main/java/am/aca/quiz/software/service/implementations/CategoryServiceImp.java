package am.aca.quiz.software.service.implementations;

import am.aca.quiz.software.entity.CategoryEntity;
import am.aca.quiz.software.repository.CategoryRepository;
import am.aca.quiz.software.service.dto.CategoryDto;
import am.aca.quiz.software.service.intefaces.CategoryService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImp implements CategoryService {


    private final CategoryRepository categoryRepository;

    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }



    @Override
    public boolean addCategory(String type) throws SQLException {
        return false;
    }

    @Override
    public List<CategoryEntity> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean update(CategoryEntity category, Long id) throws SQLException {
        return false;
    }

    @Override
    public CategoryEntity remove(CategoryEntity category) throws SQLException {
        return null;
    }

    @Override
    public boolean removeById(Long id) throws SQLException {
        return false;
    }

    @Override
    public CategoryEntity getById(Long id) throws SQLException {
        return null;
    }
}
