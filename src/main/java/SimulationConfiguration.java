import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SimulationConfiguration {

    @NotNull(message = "Height cannot be null")
    @Min(value = 2, message = "Height must be greater or equals to 2")
    @Max(value = 25, message = "Height must be lower or equals to 25")
    private int height;

    @Min(value = 2, message = "Width must be greater or equals to 2")
    @Max(value = 25, message = "Width must be lower or equals to 25")
    @NotNull(message = "Width cannot be null")
    private int width;

    @DecimalMin(value = "0.01", message = "Proba must at least be equal to 1% (0.01)")
    @DecimalMax(value = "1.00", message = "Proba can't be higher than 100% (1.00)")
    @NotNull(message = "Proba cannot be null")
    private double proba;

    @NotEmpty(message = "You do no have coordinates.")
    private List<Case> fireCoordinates;

    public SimulationConfiguration() {}

}