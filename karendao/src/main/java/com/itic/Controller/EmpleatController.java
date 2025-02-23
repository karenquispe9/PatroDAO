package com.itic.Controller;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.itic.HibernateUtil;
import com.itic.model.Departament;
import com.itic.model.Empleat;

public class EmpleatController {

    public void createEmpleat(int DNI, String nom, String cognom, int departamentId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            
            // Obtener el departamento correspondiente
            Departament departament = session.get(Departament.class, departamentId);
            
            // Crear el empleat
            Empleat empleat = new Empleat(DNI, nom, cognom, departament);
            
            // Guardar el empleat
            session.save(empleat);
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
