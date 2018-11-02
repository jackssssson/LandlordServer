package daredevil.project;

import daredevil.project.models.*;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

    @Bean
    public SessionFactory sessionFactory() {
        return new org.hibernate.cfg.Configuration()
                .configure()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Estates.class)
                .addAnnotatedClass(Messages.class)
                .addAnnotatedClass(UserRating.class)
                .buildSessionFactory();
    }
}
