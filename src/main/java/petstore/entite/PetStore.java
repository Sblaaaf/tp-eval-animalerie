package petstore.entite;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PetStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String managerName;

    // Relation 1-1 - animalerie a 1 adresse
    @OneToOne
    @JoinColumn(name = "ADDRESS_ID") // vers Address
    private Address address;

    // Relation 1-N - Une animalerie à plusieurs produits
    @OneToMany(mappedBy = "petStore")
    private Set<Product> products = new HashSet<>();

    // Relation 1-N - Animalerie possède plusieurs animaux
    @OneToMany(mappedBy = "petStore")
    private Set<Animal> animals = new HashSet<>();

    // Constructeur vide
    public PetStore() {
    }

    public PetStore(String name, String managerName) {
        this.name = name;
        this.managerName = managerName;
    }

    // MÉTHODES UTILITAIRES :
    // Ajouter un produit + bidirectionelle
    public void addProduct(Product product) {
        this.products.add(product);
        product.setPetStore(this);
    }

    // Ajoute un animal et bidirect
    public void addAnimal(Animal animal) {
        this.animals.add(animal);
        animal.setPetStore(this);
    }

    // getters Setters ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getManagerName() { return managerName; }
    public void setManagerName(String managerName) { this.managerName = managerName; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public Set<Product> getProducts() { return products; }
    public void setProducts(Set<Product> products) { this.products = products; }

    public Set<Animal> getAnimals() { return animals; }
    public void setAnimals(Set<Animal> animals) { this.animals = animals; }
}