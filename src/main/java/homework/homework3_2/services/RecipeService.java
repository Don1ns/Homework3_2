package homework.homework3_2.services;

import homework.homework3_2.model.Recipe;


import java.util.TreeMap;

public interface RecipeService {
    Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(int id);

    Recipe editRecipe(int id, Recipe recipe);

    boolean deleteRecipe(int id);

    TreeMap<Integer, Recipe> getAllRecipes();
}
