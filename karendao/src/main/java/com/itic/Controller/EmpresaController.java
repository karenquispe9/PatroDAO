package com.itic.Controller;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.itic.HibernateUtil;
import com.itic.model.Empresa;

public class EmpresaController {

    public void createEmpresa(String nom) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            
            // Crear la empresa
            Empresa empresa = new Empresa(nom);
            
            // Guardar la empresa
            session.save(empresa);
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
