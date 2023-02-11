package homework.homework3_2.services.impl;

import homework.homework3_2.model.Ingredient;
import homework.homework3_2.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;
@Service
public class IngredientServiceImpl implements IngredientService {
    private int lastId = 0;
    private final Map<Integer, Ingredient> ingredients = new TreeMap<>();
    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        ingredients.put(lastId++, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient getIngredient(int id) {
        Ingredient ingredient = ingredients.get(id);
        if(ingredient != null){
            return ingredient;
        }
        return null;
    }
    @Override
    public Ingredient editIngredient(int id, Ingredient ingredient){
        if (ingredients.containsKey(id)){
            ingredients.put(id, ingredient);
            return ingredient;
        }
        return null;
    }
    @Override
    public boolean deleteIngredient(int id){
        if (ingredients.containsKey(id)){
            ingredients.remove(id);
            return true;
        }
        return false;
    }
    @Override
    public Map<Integer, Ingredient> getAllIngredients() {
        return ingredients;
    }
}
