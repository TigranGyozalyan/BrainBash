package am.aca.quiz.software.controller;

import am.aca.quiz.software.entity.CategoryEntity;
import am.aca.quiz.software.service.implementations.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;



@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryServiceImp categoryServiceImp;

    @Autowired
    public CategoryController(CategoryServiceImp categoryServiceImp) {
        this.categoryServiceImp = categoryServiceImp;
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addCategoryPage() {
        return "category";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String postNewCategory(@RequestBody MultiValueMap<String, String> formData) {
        List<String> type = formData.get("type");
        try {
            categoryServiceImp.addCategory(type.get(0));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "category";
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CategoryEntity> getTest() {
        try {
            return categoryServiceImp.getAll();
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

