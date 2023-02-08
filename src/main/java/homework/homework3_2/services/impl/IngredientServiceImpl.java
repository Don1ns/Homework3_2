package homework.homework3_2.services.impl;

import homework.homework3_2.model.Ingredient;
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
        Ingredient ingredient = INGREDIENTS.get(id);
        if(ingredient != null){
            return ingredient;
        }
        return null;
    }
    @Override
    public Ingredient editIngredient(int id, Ingredient ingredient){
        if (INGREDIENTS.containsKey(id)){
            INGREDIENTS.put(id, ingredient);
            return ingredient;
        }
        return null;
    }
    @Override
    public boolean deleteIngredient(int id){
        if (INGREDIENTS.containsKey(id)){
            INGREDIENTS.remove(id);
            return true;
        }
        return false;
    }
    @Override
    public Map<Integer, Ingredient> getAllIngredients() {
        return INGREDIENTS;
    }
}
