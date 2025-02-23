package com.itic.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.itic.model.Empleat;

public class EmpleatDAO extends GenDAOImpl<Empleat> {
    public EmpleatDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Empleat.class);
    }
    
    public List<Empleat> obtenirTotes() throws Exception {
        try (Session session = getSessionFactory().openSession()) {
            Query<Empleat> query = session.createQuery("from Empleat", Empleat.class);
            return query.list();
        } catch (Exception e) {
            throw new Exception("Error en obtenir els empleats", e);
        }
    }

    public void eliminarEmpleat(int idEmpleat) throws Exception {
        Transaction tx = null;
        try (Session session = getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Empleat empleat = session.find(Empleat.class, idEmpleat);
            if (empleat != null) {
                session.remove(empleat);
                tx.commit();
            } else {
                System.err.println("Empleat no trobat.");
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new Exception("Error eliminant empleat", e);
        }
    }

    
}
