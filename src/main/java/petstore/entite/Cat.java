package petstore.entite;

import jakarta.persistence.Entity;
import java.util.Date;

@Entity
public class Cat extends Animal {

    private String chipId;

    public Cat() {
    }

    public Cat(Date birth, String couleur, String chipId) {
        // Appel au constructeur de la classe mère Animal
        super(birth, couleur);
        this.chipId = chipId;
    }

    public String getChipId() {
        return chipId;
    }

    public void setChipId(String chipId) {
        this.chipId = chipId;
    }
}