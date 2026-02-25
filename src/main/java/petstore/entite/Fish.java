package petstore.entite;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Fish extends Animal {

    // String "FRESH_WATER" ou "SEA_WATER"
    @Enumerated(EnumType.STRING)
    private FishLivEnv livingEnv;

    public Fish() {
    }

    public Fish(Date birth, String couleur, FishLivEnv livingEnv) {
        // Appelle Animal
        super(birth, couleur);
        this.livingEnv = livingEnv;
    }

    public FishLivEnv getLivingEnv() {
        return livingEnv;
    }

    public void setLivingEnv(FishLivEnv livingEnv) {
        this.livingEnv = livingEnv;
    }
}