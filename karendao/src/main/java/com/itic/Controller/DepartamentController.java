package com.itic.Controller;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.itic.HibernateUtil;
import com.itic.model.Departament;
import com.itic.model.Empleat;
import com.itic.model.Empresa;

public class DepartamentController {

    public void createDepartament(int gerentId, int empresaId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            
            // Obtener el gerente y la empresa correspondientes
            Empleat gerent = session.get(Empleat.class, gerentId);
            Empresa empresa = session.get(Empresa.class, empresaId);
            
            // Crear el departamento
            Departament departament = new Departament(gerent);
            departament.setEmpresa(empresa);
            
            // Guardar el departamento
            session.save(departament);
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
