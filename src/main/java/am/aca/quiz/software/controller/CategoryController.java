package am.aca.quiz.software.controller;

import am.aca.quiz.software.entity.CategoryEntity;
import am.aca.quiz.software.service.implementations.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryServiceImp categoryServiceImp;

    @RequestMapping(method = RequestMethod.GET)
    public List<CategoryEntity> getTest(Model model){
        try {
            return categoryServiceImp.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping(value = "/{c}",method = RequestMethod.POST)
    public boolean postNewCategory(@PathVariable("c") String category){
        try {
            return categoryServiceImp.addCategory(category);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public boolean updateCategory(@RequestBody CategoryEntity categoryEntity,@PathVariable("id") Long id){
        try {
            return categoryServiceImp.update(categoryEntity,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public boolean deleteCategory(@PathVariable("id")Long id){
        try {
            return categoryServiceImp.removeById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public CategoryEntity getCategoryById(@PathVariable("id") Long id){
        try {
            return categoryServiceImp.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
