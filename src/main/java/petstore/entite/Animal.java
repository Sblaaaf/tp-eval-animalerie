package petstore.entite;

import jakarta.persistence.*;
import java.util.Date;

@Entity
// Mettre en place héritage avec la stratégie JOINED (1 table par classe) :

@Inheritance(strategy = InheritanceType.JOINED)
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Date - @Temporal
    @Temporal(TemporalType.DATE) // stocke la date
    private Date birth;

    private String couleur;

    // Constructeur
    public Animal() {
    }

    public Animal(Date birth, String couleur) {
        this.birth = birth;
        this.couleur = couleur;
    }

    // Relation N-1 vers PetStore - plusieurs animaux dans 1 animalerie
    @ManyToOne
    @JoinColumn(name = "STORE_ID")
    private PetStore petStore;

    // Getters setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public PetStore getPetStore() {
        return petStore;
    }

    public void setPetStore(PetStore petStore) {
        this.petStore = petStore;
    }
}