package homework.homework3_2.services;

import homework.homework3_2.model.Ingredient;

import java.util.TreeMap;

public interface IngredientService {
    Ingredient addIngredient(Ingredient ingredient);
    Ingredient getIngredient(int id);

    Ingredient editIngredient(int id, Ingredient ingredient);

    boolean deleteIngredient(int id);

    TreeMap<Integer, Ingredient> getAllIngredients();
}
