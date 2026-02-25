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

    // L'UML demande "Date". @Temporal précise à la base qu'on ne veut stocker que la date (sans l'heure)
    @Temporal(TemporalType.DATE)
    private Date birth;

    private String couleur;

    // Constructeur
    public Animal() {
    }

    public Animal(Date birth, String couleur) {
        this.birth = birth;
        this.couleur = couleur;
    }

    // Getters Setters
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
}