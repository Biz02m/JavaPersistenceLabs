import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static EntityManagerFactory factory;
    public static EntityManager em;
    public static Scanner sc;
    public static boolean done;

    public static void main(String[] args) {
        factory = Persistence.createEntityManagerFactory("rpgPu");
        em = factory.createEntityManager();
        sc = new Scanner(System.in);

        done = false;
        while(!done){
            System.out.println("Put in:");
            System.out.println("quit | new entry | delete entry | print all | special queries");
            switch (sc.nextLine()) {
                case "quit" -> done = true;
                case "new entry" -> newEntry();
                case "delete entry" -> deleteEntry();
                case "print all" -> printAll();
                case "special queries" -> specialQueries();
                case "test" -> test();
            }
        }

    }

    public static void newEntry(){
        System.out.println("Put in: tower | mage");
        String name = sc.nextLine();

        switch (name) {
            case "tower" -> {
                System.out.println("Put in: name , height of the tower");
                name = sc.nextLine();
                int height = sc.nextInt();
                sc.nextLine();
                em.getTransaction().begin();
                em.persist(new Tower(name, height));
                em.getTransaction().commit();
            }
            case "mage" -> {
                System.out.println("Put in: name , level, name of the tower");
                name = sc.nextLine();
                int level = sc.nextInt();
                sc.nextLine();
                String towerName = sc.nextLine();
                if (!towerName.equals("")) {
                    Tower tower = em.find(Tower.class, towerName);
                    if(tower != null){
                        em.persist(new Mage(name,level,tower));
                    }else{
                        System.out.println("Tower not found in database, not committing");
                    }
                } else {
                    System.out.println("name of the tower not given, not committing to database");
                }
            }
            default -> System.out.println("Wrong input");
        }

    }

    public static void deleteEntry(){

    }

    public static void printAll(){
        System.out.println("-=================-");
        System.out.println("Database Contents");
        System.out.println("-=================-");

        System.out.println("Towers:");
        Query towersQ = em.createQuery("SELECT t FROM Tower t", Tower.class);
        List<Tower> towers = towersQ.getResultList();
        for(Tower t: towers){
            System.out.println(t);
            List<Mage> mageList= t.getMages();
            for(Mage m: mageList){
                System.out.println(m);
            }
        }

        System.out.println("Mages:");
        Query magesQ = em.createQuery("SELECT p FROM Mage p", Mage.class);
        List<Mage> mages = magesQ.getResultList();
        for (Mage m: mages) {
            System.out.println(m);
        }

    }

    public static void specialQueries(){

    }

    public static void test(){

        em.getTransaction().begin();
        Tower tower = new Tower("White tower", 99);
        em.persist(tower);

        em.persist(new Mage("Gandalf the white", 99, tower));
        em.persist(new Mage("Gandalf the brown", 69, tower));
        em.persist(new Mage("Gandalf the gray", 8, tower));
        em.getTransaction().commit();

        Query query = em.createQuery("SELECT p FROM Mage p", Mage.class);
        List<Mage> mages = query.getResultList();
        for (Mage m: mages) {
            System.out.println(m.getName());
        }
    }
}
