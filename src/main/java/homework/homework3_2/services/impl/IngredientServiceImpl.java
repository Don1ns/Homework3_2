package homework.homework3_2.services.impl;

import homework.homework3_2.model.Ingredient;
import homework.homework3_2.model.Recipe;
import homework.homework3_2.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;
@Service
public class IngredientServiceImpl implements IngredientService {
    private static int lastId = 0;
    private static final Map<Integer, Ingredient> INGREDIENTS = new TreeMap<>();
    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        INGREDIENTS.put(lastId++, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient getIngredient(int id) {
        return INGREDIENTS.get(id);
    }
}
