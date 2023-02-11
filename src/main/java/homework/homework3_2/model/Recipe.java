package homework.homework3_2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;
@Data
@AllArgsConstructor
public class Recipe {
    private String title;
    private int cookingTime;
    private List<Ingredient> ingredients;
    private List<String> cookingSteps;
}
