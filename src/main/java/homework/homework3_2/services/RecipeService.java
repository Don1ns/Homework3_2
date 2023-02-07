package homework.homework3_2.services;

import homework.homework3_2.model.Recipe;

public interface RecipeService {
    Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(int id);
}
