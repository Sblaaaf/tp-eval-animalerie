package petstore.entite;

import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String label;

    // FOOD, ACCESSORY, CLEANING
    @Enumerated(EnumType.STRING)
    private ProdType type;

    private double price;

    // Relation N-1 - Plusieurs produit pour 1 animalerie
    @ManyToOne
    @JoinColumn(name = "STORE_ID") // Clé étrangère dans la table Product
    private PetStore petStore;

    // Constructeur vide
    public Product() {
    }

    public Product(String code, String label, ProdType type, double price) {
        this.code = code;
        this.label = label;
        this.type = type;
        this.price = price;
    }

    // Getters Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public ProdType getType() { return type; }
    public void setType(ProdType type) { this.type = type; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public PetStore getPetStore() { return petStore; }
    public void setPetStore(PetStore petStore) { this.petStore = petStore; }
}