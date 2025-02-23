package com.itic.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.itic.model.Tasques;

public class TasquesDAO extends GenDAOImpl<Tasques> {

    public TasquesDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Tasques.class);
    }

    public List<Tasques> obtenirTotes() throws Exception {
        try (Session session = getSessionFactory().openSession()) {
            Query<Tasques> query = session.createQuery("from Tasques", Tasques.class);
            return query.list();
        } catch (Exception e) {
            throw new Exception("Error obtenint les tasques", e);
        }
    }

    public void eliminarTasques(int idTasques) throws Exception {
        Transaction tx = null;
        try (Session session = getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Tasques tasca = session.find(Tasques.class, idTasques);
            if (tasca != null) {
                session.remove(tasca);
                tx.commit();
            } else {
                System.err.println("Tasca no trobada.");
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new Exception("Error eliminant tasca", e);
        }
    }

    public Tasques find(int id) throws Exception {
        try (Session session = getSessionFactory().openSession()) {
            return session.get(Tasques.class, id);
        } catch (Exception e) {
            throw new Exception("Error finding Tasques by id", e);
        }
    }
}
