package pl.pwn.reaktor.dziekanat.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import pl.pwn.reaktor.dziekanat.model.Student;
import pl.pwn.reaktor.dziekanat.model.Survey;
import pl.pwn.reaktor.dziekanat.model.User;

public class HibernateUtils {

    private static final SessionFactory SESSION_FACTORY;

    private HibernateUtils() {

        throw new IllegalStateException("HibernateUtils is only utility class");
    }

    static {
        try {

            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();
//              Dzięki addAnnotatedClass mamy automatyczne mapowanie danej klasy na bazie danych.
//            Musimy dla każdej Entity (encji) dodawać nowy wpis żeby działało.
//            MetadataSources sources = new MetadataSources(serviceRegistry)
//                    .addAnnotatedClass(User.class);

            MetadataSources sources = new MetadataSources(serviceRegistry)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Survey.class);

            Metadata metadata = sources.getMetadataBuilder()
                    .build();

            SESSION_FACTORY = metadata.getSessionFactoryBuilder()
                    .build();

        } catch (Exception e){
            System.err.println("Session Factory could not be created. \n" + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void initSessionFactory() {
        System.out.println("Initialize Hibernate session factory");
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public static void destroySessionFactory(){
        System.out.println("Destroy Hibernate session factory");
        SESSION_FACTORY.close();
    }
}
