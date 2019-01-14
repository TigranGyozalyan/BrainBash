package am.aca.quiz_software;

import am.aca.quiz_software.entities.CategoryEntity;
import am.aca.quiz_software.repositories.CategoryRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        CategoryRepository categoryRepository = ctx.getBean(CategoryRepository.class);

        CategoryEntity categoryEntity = new CategoryEntity("Programing");

        categoryRepository.save(categoryEntity);
        categoryRepository.flush();

    }
}
