package am.aca.quiz.software;


import am.aca.quiz.software.entity.UserEntity;
import am.aca.quiz.software.service.ThreadService;
import am.aca.quiz.software.service.implementations.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

@SpringBootApplication
@EnableAsync
public class Quiz implements CommandLineRunner {


    @Autowired
    private ThreadService threadService;

    public static void main(String[] args) {

        SpringApplication.run(Quiz.class, args);

    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }


    @Bean("threadPoolTaskExecutor")
    public TaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(1000);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setThreadNamePrefix("Async-");
        return executor;
    }



    @Override
    public void run(String...args) throws Exception {
      threadService.findUser();
    }
}
