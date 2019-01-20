package am.aca.quiz.software;


import am.aca.quiz.software.config.DataBaseConfig;
import am.aca.quiz.software.entity.*;
import am.aca.quiz.software.service.implementations.CategoryServiceImp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        ApplicationContext ctx = new AnnotationConfigApplicationContext(DataBaseConfig.class);

        //  menu();
    }

    private static void menu() {

        Scanner in = new Scanner(System.in);
        System.out.println("Choose User or Admin");
        String authentication;
        boolean stayInLoop = true;
        while (stayInLoop) {
            stayInLoop = false;
            authentication = in.nextLine();
            switch (authentication.toLowerCase()) {
                case "user":
                    System.out.println("Opening Login Page Enter your Email and Password ");
                    System.out.println("stex authentication and authorisation  kgna @ ete sax lava ancnuma myus ej");
                    System.out.println("*************************************************************");
                    System.out.println("For Category Press c" +
                            "\n For History Press h" +
                            "\n For Profile press p" +
                            "\n To LogOut press o");
                    String button = in.nextLine();
                    switch (button) {
                        case "c":
                            System.out.println("Choose Category");
                            String tab = in.nextLine();
                            switch (tab.toLowerCase()) {
                                case "programming":
                                    System.out.println("Choose Language");
                                    boolean stayInSwitc = true;
                                    while (stayInLoop) {
                                        stayInLoop = false;
                                        String language = in.nextLine();
                                        switch (language.toLowerCase()) {
                                            case "java":
                                                System.out.println("Choose Test name");
                                                String testName = in.nextLine();

                                                switch (testName.toLowerCase()) {
                                                    case "test1":
                                                        break;
                                                    case "test2":
                                                        break;
                                                    default:
                                                        stayInLoop = true;
                                                        System.out.println("No Such Test Found");
                                                        break;
                                                }
                                                break;
                                            case "c#":
                                                break;
                                        }
                                    }
                                    break;
                                case "networking":
                                    break;
                                case "DataBases":
                                    break;
                                default:
                                    System.out.println("No Such Category Found");
                            }
                            break;
                        case "o":
                            System.exit(0);
                            break;
                        case "h":
                            break;
                        case "p":
                            break;
                    }

                    break;
                case "admin":
                    break;
                default:
                    System.out.println("Try Again");
                    stayInLoop = true;
                    break;
            }

        }

    }

    private static void foo() {
        CategoryEntity categoryEntity = new CategoryEntity("Programming");

        SubCategoryEntity subCategoryEntity = new SubCategoryEntity("Java", categoryEntity);

        TopicEntity topicEntity = new TopicEntity("OSI", subCategoryEntity);

        QuestionEntity questionEntity = new QuestionEntity("tarberutyuny int-i u Intageri", QuestionEntity.Level.valueOf("biginner"), 2, 3, topicEntity);

        AnswerEntity answerEntity = new AnswerEntity("patasxan", "bacatrutyun", true, questionEntity);

        UserEntity userEntity = new UserEntity("name", "username", "email", "nickname", "password", false);

        ScoreEntity scoreEntity = new ScoreEntity(5.5, 1, topicEntity, userEntity);
        LocalDateTime localDateTime = LocalDateTime.now();


        TestEntity testEntity = new TestEntity("test1", localDateTime, "discription");


        HistoryEntity historyEntity = new HistoryEntity(localDateTime, HistoryEntity.Status.INPROGRESS, 5.3, userEntity, testEntity);

    }
}
