package com.itic.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.itic.model.Departament;

public class DepartamentDAO extends GenDAOImpl<Departament> {

    public DepartamentDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Departament.class);
    }

    public List<Departament> obtenirTots() throws Exception {
        try (Session session = getSessionFactory().openSession()) {
            Query<Departament> query = session.createQuery("from Departament", Departament.class);
            return query.list();
        } catch (Exception e) {
            throw new Exception("Error en obtenir els departaments", e);
        }
    }

    public void eliminarDepartament(int idDepartament) throws Exception {
        Transaction tx = null;
        try (Session session = getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Departament departament = session.find(Departament.class, idDepartament);
            if (departament != null) {
                session.remove(departament);
                tx.commit();
            } else {
                System.err.println("Departament no trobat.");
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new Exception("Error eliminant departament", e);
        }
    }

    public Departament find(int id) throws Exception {
        try (Session session = getSessionFactory().openSession()) {
            return session.get(Departament.class, id);
        } catch (Exception e) {
            throw new Exception("Error finding Departament by id", e);
        }
    }
}
