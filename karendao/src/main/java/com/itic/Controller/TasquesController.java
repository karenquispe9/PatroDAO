package com.itic.Controller;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.itic.HibernateUtil;
import com.itic.model.Empleat;
import com.itic.model.Tasques;

public class TasquesController {

    public void createTasques(String descripcio, int empleatPrincipalId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            
            // Obtener el empleat principal
            Empleat empleatPrincipal = session.get(Empleat.class, empleatPrincipalId);
            
            // Crear la tarea
            Tasques tasques = new Tasques(descripcio, empleatPrincipal);
            
            // Guardar la tarea
            session.save(tasques);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
