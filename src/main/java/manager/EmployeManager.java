package manager;

import entities.Employes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;



/*

    1 controller
    1 service
    1 repo
 */
public class EmployeManager {
    protected SessionFactory sessionFactory;

    private void setup() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        } catch (Exception ex) {
            StandardServiceRegistryBuilder.destroy(registry);
            ex.getStackTrace();
        }
    }

    protected void exit() {
        sessionFactory.close();
    }

    // creation d'un employes
    protected void create() {
        Employes louis = new Employes();
        louis.setNom("jojo");
        louis.setPrenom("jiji");
        louis.setEmail("jiji@gmail.com");
        louis.setAge(35);
        louis.setFonction("infirmière");
        louis.setTel("0610302010");
        louis.setAdresse("2 rue des alouettes, 75000, Paris");

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(louis);
        session.getTransaction().commit();
        session.close();
    }

// affichage en console ensemble d'un employé by id

    /**
     * @param id
     * @return
     */
    protected Employes read(long id) {
        Session session = sessionFactory.openSession();
        Employes employes = session.get(Employes.class, id);
        System.out.println("nom " + employes.getNom());
        System.out.println("prenom " + employes.getPrenom());
        System.out.println("email " + employes.getEmail());
        System.out.println("age " + employes.getAge());
        System.out.println("fonction " + employes.getFonction());
        System.out.println("tel " + employes.getTel());
        System.out.println("adresse " + employes.getAdresse());
        session.close();
        return employes;
    }

    // mise à jour d'un employé

    /**
     * @param id
     * @param newEmployes
     */
    protected void update(long id, Employes newEmployes) {
        Employes employes = this.read(id);

        if (newEmployes.getNom() != null) {
            employes.setNom(newEmployes.getNom());
        }
        if (newEmployes.getPrenom() != null) {
            employes.setPrenom(newEmployes.getPrenom());
        }
        if (newEmployes.getEmail() != null) {
            employes.setEmail(newEmployes.getEmail());
        }
        if (newEmployes.getAge() < 0) {
            employes.setAge(newEmployes.getAge());
        }
        if (newEmployes.getFonction() != null) {
            employes.setFonction(newEmployes.getFonction());
        }
        if (newEmployes.getTel() != null) {
            employes.setTel(newEmployes.getTel());
        }
        if (newEmployes.getAdresse() != null) {
            employes.setAdresse(newEmployes.getAdresse());
        }
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(employes);
        session.getTransaction().commit();
        session.close();
    }

    // suppression d'un employes

    /**
     * @param employes
     */
    protected void delete(Employes employes) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(employes);
        session.getTransaction().commit();
        session.close();
    }


    public static void main(String[] args) {
        EmployeManager manager = new EmployeManager();
        manager.setup();
        manager.create();
        // manager.read(2);
        //Employes employes = manager.read(4);
       // employes.setFonction("Scrum master");
       // long id = 3;
       // manager.update(id, employes);
       // manager.delete(employes);
        manager.exit();
    }
}











