package com.example.demo;

import com.example.demo.models.*;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LandlordCommunicationRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(LandlordCommunicationRestApplication.class, args);
    }

    @Bean
    public SessionFactory sessionFactory() {
        return new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(BankAccount.class)
                .addAnnotatedClass(ContentType.class)
                .addAnnotatedClass(Estates.class)
                .addAnnotatedClass(MessageContent.class)
                .addAnnotatedClass(Messages.class)
                .addAnnotatedClass(Transactions.class)
                .addAnnotatedClass(UserRating.class)
                .addAnnotatedClass(UserType.class)
                .buildSessionFactory();
    }

}
