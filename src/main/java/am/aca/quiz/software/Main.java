package am.aca.quiz.software;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.Scanner;

@SpringBootApplication
public class Main extends SpringBootServletInitializer {

    public static void main(String[] args) {

        SpringApplication.run(Main.class,args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Main.class);
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



}
