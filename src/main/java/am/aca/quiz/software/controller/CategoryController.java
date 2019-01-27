package am.aca.quiz.software.controller;

import am.aca.quiz.software.entity.CategoryEntity;
import am.aca.quiz.software.service.dto.CategoryDto;
import am.aca.quiz.software.service.implementations.CategoryServiceImp;
import am.aca.quiz.software.service.mapper.CategoryMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryServiceImp categoryServiceImp;
    private final CategoryMapper categoryMapper;


    public CategoryController(CategoryServiceImp categoryServiceImp, CategoryMapper categoryMapper) {
        this.categoryServiceImp = categoryServiceImp;
        this.categoryMapper = categoryMapper;
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addCategoryPage() {
        return new ModelAndView("view/category");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView postNewCategory(@RequestParam("type") String type) {
        ModelAndView modelAndView = new ModelAndView("view/category");
        try {
            categoryServiceImp.addCategory(type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelAndView;
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

