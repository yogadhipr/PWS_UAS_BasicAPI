/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UAS_PWS.UAS_PWS;

import UAS_PWS.UAS_PWS.exceptions.NonexistentEntityException;
import UAS_PWS.UAS_PWS.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author TUF GAMING
 */
public class BaranggJpaController implements Serializable {

    public BaranggJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    //ini
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("UAS_PWS_UAS_PWS_jar_0.0.1-SNAPSHOTPU");

    
    //itu
    public BaranggJpaController(){};
    

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Barangg barangg) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(barangg);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBarangg(barangg.getId()) != null) {
                throw new PreexistingEntityException("Barangg " + barangg + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Barangg barangg) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            barangg = em.merge(barangg);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = barangg.getId();
                if (findBarangg(id) == null) {
                    throw new NonexistentEntityException("The barangg with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Barangg barangg;
            try {
                barangg = em.getReference(Barangg.class, id);
                barangg.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The barangg with id " + id + " no longer exists.", enfe);
            }
            em.remove(barangg);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Barangg> findBaranggEntities() {
        return findBaranggEntities(true, -1, -1);
    }

    public List<Barangg> findBaranggEntities(int maxResults, int firstResult) {
        return findBaranggEntities(false, maxResults, firstResult);
    }

    private List<Barangg> findBaranggEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Barangg.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Barangg findBarangg(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Barangg.class, id);
        } finally {
            em.close();
        }
    }

    public int getBaranggCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Barangg> rt = cq.from(Barangg.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
