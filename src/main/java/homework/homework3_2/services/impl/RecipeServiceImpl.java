package homework.homework3_2.services.impl;

import homework.homework3_2.model.Recipe;
import homework.homework3_2.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;
@Service
public class RecipeServiceImpl implements RecipeService {
    private int lastId = 0;
    private final Map<Integer, Recipe> recipes = new TreeMap<>();
    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipes.put(lastId++, recipe);
        return  recipe;
    }

    @Override
    public Recipe getRecipe(int id) {
        return recipes.get(id);
    }
    @Override
    public Recipe editRecipe(int id, Recipe recipe){
        if (recipes.containsKey(id)){
            recipes.put(id, recipe);
            return recipe;
        }
        return null;
    }
    @Override
    public boolean deleteRecipe(int id){
        if (recipes.containsKey(id)){
            recipes.remove(id);
            return true;
        }
        return false;
    }
    @Override
    public Map<Integer, Recipe> getAllRecipes() {
        return recipes;
    }
}
