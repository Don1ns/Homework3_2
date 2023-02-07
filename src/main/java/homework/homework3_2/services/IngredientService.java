package homework.homework3_2.services;

import homework.homework3_2.model.Ingredient;

public interface IngredientService {
    Ingredient addIngredient(Ingredient ingredient);
    Ingredient getIngredient(int id);
}
