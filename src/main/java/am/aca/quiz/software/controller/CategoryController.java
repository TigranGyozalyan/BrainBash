package am.aca.quiz.software.controller;

import am.aca.quiz.software.entity.CategoryEntity;
import am.aca.quiz.software.service.dto.CategoryDto;
import am.aca.quiz.software.service.implementations.CategoryServiceImp;
import am.aca.quiz.software.service.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;


@Controller  //if we change to @RestControllers jsp will not to work
@RequestMapping("/category")
public class CategoryController {

    private final CategoryServiceImp categoryServiceImp;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryController(CategoryServiceImp categoryServiceImp, CategoryMapper categoryMapper) {
        this.categoryServiceImp = categoryServiceImp;
        this.categoryMapper = categoryMapper;
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addCategoryPage() {
        return "category";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String postNewCategory(/*@RequestBody MultiValueMap<String, String> formData*/ @RequestParam("type") String type) {
//        List<String> type = formData.get("type");
//        try {
//            categoryServiceImp.addCategory(type.get(0));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        try {
            categoryServiceImp.addCategory(type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "category";
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CategoryDto> getTest() {

        try {
            return categoryMapper.mapEntitiesToDto(categoryServiceImp.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PATCH)
    public void updateCategory(@RequestBody CategoryEntity categoryEntity, @PathVariable("id") Long id) {
        try {
            categoryServiceImp.update(categoryEntity, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteCategory(@PathVariable("id") Long id) {
        try {
            categoryServiceImp.removeById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CategoryEntity getCategoryById(@PathVariable("id") Long id) {
        try {
            return categoryServiceImp.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}

