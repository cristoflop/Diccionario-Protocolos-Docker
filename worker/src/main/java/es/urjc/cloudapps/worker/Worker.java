package es.urjc.cloudapps.worker;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Worker {

    public static void main(String[] args) {
        SpringApplication.run(Worker.class, args);
    }

    @Bean
    public Queue tasksProgress() {
        return new Queue("tasksProgress", false);
    }

    @Bean
    public Queue newTasks() {
        return new Queue("createTask", false);
    }

}
