package am.aca.quiz_software.service;

import am.aca.quiz_software.entity.SubCategoryEntity;
import am.aca.quiz_software.repository.CategoryRepository;
import am.aca.quiz_software.repository.SubCategoryRepository;
import am.aca.quiz_software.repository.TopicRepository;
import am.aca.quiz_software.service.dto.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class SubCategoryServiceImp implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;
    private final TopicRepository topicRepository;

    @Autowired
    public SubCategoryServiceImp(SubCategoryRepository subCategoryRepository, CategoryRepository categoryRepository, TopicRepository topicRepository) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryRepository = categoryRepository;
        this.topicRepository = topicRepository;
    }

    public void addCategory(SubCategoryEntity subCategory) throws SQLException {

    }

    public List<SubCategoryEntity> getAll() throws SQLException {
        return null;
    }

    public void update(SubCategoryEntity subCategory) throws SQLException {

    }

    public void remove(SubCategoryEntity subCategory) throws SQLException {

    }
}
