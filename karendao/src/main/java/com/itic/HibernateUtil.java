package com.itic;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
//Para asegurar que la clase no pueda ser instanciada nuevamente 
//se regula el alcance del constructor con el atributo private
    private static final SessionFactory sessionFactory = buildSessionFactory();
    
    private static SessionFactory buildSessionFactory(){
        try{
            return new Configuration()
                    .configure()
                    .buildSessionFactory(new StandardServiceRegistryBuilder()
                    .configure()
                    .build());
                } catch (HibernateException ex) {
            System.err.println("HibernateException during SessionFactory creation: " + ex);
            throw new ExceptionInInitializerError(ex);
        } catch (Exception ex) {
            System.err.println("Exception during SessionFactory creation: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}

