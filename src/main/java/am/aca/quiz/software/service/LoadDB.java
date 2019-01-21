package am.aca.quiz.software.service;

import am.aca.quiz.software.service.implementations.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class LoadDB {
    CategoryServiceImp categoryServiceImp;

    @Autowired
    public LoadDB(CategoryServiceImp categoryServiceImp){
        this.categoryServiceImp=categoryServiceImp;
    }


    public void loadCategory(){
        try {
            categoryServiceImp.addCategory("programming");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
