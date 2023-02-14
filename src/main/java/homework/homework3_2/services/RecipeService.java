package homework.homework3_2.services;

import homework.homework3_2.model.Recipe;


import java.util.Map;

public interface RecipeService {
    Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(int id);

    Recipe editRecipe(int id, Recipe recipe);

    boolean deleteRecipe(int id);

    Map<Integer, Recipe> getAllRecipes();
}
