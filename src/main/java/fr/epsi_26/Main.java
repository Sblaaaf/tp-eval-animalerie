package fr.epsi_26;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import petstore.entite.*;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Init JPA avec petstore définie dans persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("petstore");
        EntityManager em = emf.createEntityManager();

        // Début de la transaction pour insérer des données
        em.getTransaction().begin();

        try {
            // test avec 3 adresses
            // em.persist pour la clé etrangere de petstore
            Address addr1 = new Address("10", "Rue des Chats", "44000", "Nantes");
            Address addr2 = new Address("20", "rue des poissons", "44000", "Nantes");
            Address addr3 = new Address("30", "Rue des Chiens", "44000", "Nantes");
            em.persist(addr1);
            em.persist(addr2);
            em.persist(addr3);

            // inserer 3 petsotre - 1-1
            PetStore store1 = new PetStore("petstore 1", "Jean jean");
            store1.setAddress(addr1);

            PetStore store2 = new PetStore("petstore 2", "Bob bob");
            store2.setAddress(addr2);

            PetStore store3 = new PetStore("petstore 3", "kevin kevin");
            store3.setAddress(addr3);

            em.persist(store1);
            em.persist(store2);
            em.persist(store3);

            // 3 PRODUITS
            Product p1 = new Product("P1", "Croquettes", ProdType.FOOD, 10);
            Product p2 = new Product("P2", "Aquarium", ProdType.ACCESSORY, 10);
            Product p3 = new Product("P3", "Brosse", ProdType.CLEANING, 10);

            // appel des méthodes pour relation product - petsotre
            store1.addProduct(p1);
            store2.addProduct(p2);
            store3.addProduct(p3);

            em.persist(p1);
            em.persist(p2);
            em.persist(p3);

            // Ajout des animaux (3 par table)
            // CATS
            Cat cat1 = new Cat(new Date(), "Noir", "CHIP1");
            Cat cat2 = new Cat(new Date(), "Blanc", "CHIP2");
            Cat cat3 = new Cat(new Date(), "Roux", "CHIP3");
            // associe par exemple à petstore 1
            store1.addAnimal(cat1);
            store1.addAnimal(cat2);
            store1.addAnimal(cat3);
            em.persist(cat1);
            em.persist(cat2);
            em.persist(cat3);

            // FISH
            Fish fish1 = new Fish(new Date(), "Rouge", FishLivEnv.FRESH_WATER);
            Fish fish2 = new Fish(new Date(), "Jaune", FishLivEnv.SEA_WATER);
            Fish fish3 = new Fish(new Date(), "Gris", FishLivEnv.FRESH_WATER);
            // petsore 2
            store2.addAnimal(fish1);
            store2.addAnimal(fish2);
            store2.addAnimal(fish3);
            em.persist(fish1);
            em.persist(fish2);
            em.persist(fish3);

            // Validation
            em.getTransaction().commit();
            System.out.println("Insertion réussie");


            // REQUÊTE JPQL :
            // extraire tous les animaux d'une animalerie

            String storeNameRecherche = "petstore 2";
            // Recherche des Animaux associés au petStore
            TypedQuery<Animal> query = em.createQuery(
                    "SELECT a FROM Animal a WHERE a.petStore.name = :storeName", Animal.class);
            query.setParameter("storeName", storeNameRecherche);

            List<Animal> animauxTrouves = query.getResultList();

            System.out.println("Animaux de l'animalerie '" + storeNameRecherche + "' :");
            if (animauxTrouves.isEmpty()) {
                System.out.println("aucun animal");
            } else {
                for (Animal a : animauxTrouves) {
                    // héritage - verifier le type de l'animal
                    if (a instanceof Fish) {
                        System.out.println("fish ID: " + a.getId() + ", couleur: " + a.getCouleur());
                    } else if (a instanceof Cat) {
                        System.out.println("cat ID: " + a.getId() + ", couleur: " + a.getCouleur());
                    } else {
                        System.out.println("pet ID: " + a.getId() + ",couleur: " + a.getCouleur());
                    }
                }
            }

        } catch (Exception e) {
            // Si bug >>> annule
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}