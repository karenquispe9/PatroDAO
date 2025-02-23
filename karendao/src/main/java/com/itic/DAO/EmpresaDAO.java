package com.itic.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.itic.model.Empresa;

public class EmpresaDAO extends GenDAOImpl<Empresa> {

    public EmpresaDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Empresa.class);
    }

    public List<Empresa> obtenirTotes() throws Exception {
        try (Session session = getSessionFactory().openSession()) {
            Query<Empresa> query = session.createQuery("from Empresa", Empresa.class);
            return query.list();
        } catch (Exception e) {
            throw new Exception("Error en obtenir les empreses", e);
        }
    }

    public void eliminarEmpresa(int idEmpresa) throws Exception {
        Transaction tx = null;
        try (Session session = getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Empresa empresa = session.find(Empresa.class, idEmpresa);
            if (empresa != null) {
                session.remove(empresa);
                tx.commit();
            } else {
                System.err.println("Empresa no trobada.");
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new Exception("Error eliminant empresa", e);
        }
    }
}
