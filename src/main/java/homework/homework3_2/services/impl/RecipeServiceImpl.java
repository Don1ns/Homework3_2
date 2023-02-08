package homework.homework3_2.services.impl;

import homework.homework3_2.model.Recipe;
import homework.homework3_2.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;
@Service
public class RecipeServiceImpl implements RecipeService {
    private static int lastId = 0;
    private static final Map<Integer, Recipe> RECIPES = new TreeMap<>();
    @Override
    public Recipe addRecipe(Recipe recipe) {
        RECIPES.put(lastId++, recipe);
        return  recipe;
    }

    @Override
    public Recipe getRecipe(int id) {
        return RECIPES.get(id);
    }
    @Override
    public Recipe editRecipe(int id, Recipe recipe){
        if (RECIPES.containsKey(id)){
            RECIPES.put(id, recipe);
            return recipe;
        }
        return null;
    }
    @Override
    public boolean deleteRecipe(int id){
        if (RECIPES.containsKey(id)){
            RECIPES.remove(id);
            return true;
        }
        return false;
    }
    @Override
    public Map<Integer, Recipe> getAllRecipes() {
        return RECIPES;
    }
}
