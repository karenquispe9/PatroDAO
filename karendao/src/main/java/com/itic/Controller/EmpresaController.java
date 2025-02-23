package com.itic.Controller;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.itic.HibernateUtil;
import com.itic.model.Empresa;

public class EmpresaController {

    public void createEmpresa(String descripcio) {
        if (descripcio == null || descripcio.isEmpty()) {
            System.out.println("La descripción no puede ser nula o vacía.");
            return;
        }

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            
            // Crear la empresa con la descripción proporcionada
            Empresa empresa = new Empresa(descripcio);
            
            // Guardar la empresa
            session.save(empresa);
            transaction.commit();
            
            System.out.println("Empresa creada exitosamente.");
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
