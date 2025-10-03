import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class Simulation {

    private final SimulationConfiguration configuration;
    private ArrayList<ArrayList<Case>> forest = new ArrayList<>();
    private ArrayList<Case> casesFire = new ArrayList<>();
    private int step = 0;

    public Simulation() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        this.configuration = objectMapper.readValue(new File("src/main/resources/config.json"), SimulationConfiguration.class);

        try {
            Set<ConstraintViolation<SimulationConfiguration>> violations = validator.validate(this.configuration);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
            startSimulation();

        } catch (ConstraintViolationException e) {
            for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
                System.out.println(violation.getMessage());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Simulation();
    }

    private void startSimulation() throws IOException {
        buildForest();
        startFires();

        while (!casesFire.isEmpty()) {
            System.out.printf("Etape %d : \n", step);
            showForest();
            propagate();
            step++;
        }

        System.out.printf("Fin de la simulation à l'étape %d", step);
    }

    private void buildForest() {
        int height = this.configuration.getHeight();
        int width = this.configuration.getWidth();

        ArrayList<Case> row = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            row.add(new Case());
        }

        for (int y = 0; y < width; y++) {
            forest.add(row);
        }
    }


    private void startFires() {
        for (Case fireCase : this.configuration.getFireCoordinates()) {
            this.forest.get(fireCase.getX()).get(fireCase.getY()).setEtat(CaseEtat.FEU);
            this.casesFire.add(this.forest.get(fireCase.getX()).get(fireCase.getY()));
        }
    }

    private void propagate() {
        ArrayList<Case> propagated = new ArrayList<>();
        for (Case c : this.casesFire) {
            c.setEtat(CaseEtat.CENDRE);
            casesFire.remove(c);
            handleNewFires(c, propagated);
        }
        this.casesFire.addAll(propagated);
    }

    private void handleNewFires(Case c, ArrayList<Case> propagated) {
        Case nearCase;
        if (c.getX() - 1 > 0) {
            nearCase = this.forest.get(c.getX() - 1).get(c.getY());
            if (nearCase.doesCaseBurn(this.configuration.getProba())) {
                propagated.add(nearCase);
            }
        }
        if (c.getX() + 1 < this.forest.size() - 1) {
            nearCase = this.forest.get(c.getX() + 1).get(c.getY());
            if (nearCase.doesCaseBurn(this.configuration.getProba())) {
                propagated.add(nearCase);
            }
        }
        if (c.getY() - 1 > 0) {
            nearCase = this.forest.get(c.getX()).get(c.getY() - 1);
            if (nearCase.doesCaseBurn(this.configuration.getProba())) {
                propagated.add(nearCase);
            }
        }
        if (c.getY() + 1 < this.forest.getFirst().size()) {
            nearCase = this.forest.get(c.getX()).get(c.getY() + 1);
            if (nearCase.doesCaseBurn(this.configuration.getProba())) {
                propagated.add(nearCase);
            }
        }
    }

    private void showForest() throws IOException {
        for (int i = 0; i < this.configuration.getHeight(); i++) {
            for (int j = 0; j < this.configuration.getWidth(); j++) {
                System.out.print(this.forest.get(i).get(j).getEtat() + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();

        System.in.read();
    }

}