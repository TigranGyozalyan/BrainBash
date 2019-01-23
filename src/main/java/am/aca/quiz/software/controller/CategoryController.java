package am.aca.quiz.software.controller;

import am.aca.quiz.software.entity.CategoryEntity;
import am.aca.quiz.software.service.implementations.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImp categoryServiceImp;


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addCategoryPage() {
        return "category";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postNewCategory(@RequestBody MultiValueMap<String, String> formData) {
        String type = formData.get("type").get(0);
        try {
            categoryServiceImp.addCategory(type);
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
