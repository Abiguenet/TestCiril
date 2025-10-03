import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class Case {

    private CaseEtat etat;

    private int x;
    private int y;

    public String getEtat() {
        return new String(Character.toChars(this.etat.unicode));
    }

    public Case (){
         this.etat = CaseEtat.BOIS;
    }

    public boolean doesCaseBurn(double proba) {
        boolean result = false;
        if (Math.random() < proba && isCaseBurnable()) {
            result = true;
            this.etat = CaseEtat.FEU;
        }
        return result;
    }

    public boolean isCaseBurnable() {
        return this.etat == CaseEtat.BOIS;
    }
}

//POJO des coordonnées
record Point(int x, int y) {}