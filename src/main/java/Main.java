import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("rpgPu");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        Tower tower = new Tower("White tower", 99);
        em.persist(tower);
        em.getTransaction().commit();

        em.persist(new Mage("Gandalf the white", 99));
        em.persist(new Mage("Gandalf the brown", 69));
        em.persist(new Mage("Gandalf the gray", 8));
        em.getTransaction().commit();



        Query query = em.createQuery("SELECT p FROM Mage p", Mage.class);
        List<Mage> mages = query.getResultList();
        for (Mage m: mages) {
            System.out.println(m.getName());
        }
        em.close();
    }
}
