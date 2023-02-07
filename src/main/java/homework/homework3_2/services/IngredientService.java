package homework.homework3_2.services;

import homework.homework3_2.model.Ingredient;

public interface IngredientService {
    void addIngredient(Ingredient ingredient);
    Ingredient getIngredient(int id);
}
