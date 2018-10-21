//package daredevil.project.config;
//
//import daredevil.project.models.*;
//import org.hibernate.SessionFactory;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@EnableAutoConfiguration
//public class Config {
//    @Bean
//    public SessionFactory sessionFactory() {
//        return new org.hibernate.cfg.Configuration()
//                .configure()
//                .addAnnotatedClass(User.class)
//                .addAnnotatedClass(Address.class)
//                .addAnnotatedClass(BankAccount.class)
//                .addAnnotatedClass(ContentType.class)
//                .addAnnotatedClass(Estates.class)
//                .addAnnotatedClass(MessageContent.class)
//                .addAnnotatedClass(Messages.class)
//                .addAnnotatedClass(Transactions.class)
//                .addAnnotatedClass(UserRating.class)
//                .addAnnotatedClass(UserType.class)
//                .buildSessionFactory();
//    }
//}
