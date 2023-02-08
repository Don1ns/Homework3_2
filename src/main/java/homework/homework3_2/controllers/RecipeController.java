package homework.homework3_2.controllers;

import homework.homework3_2.model.Ingredient;
import homework.homework3_2.model.Recipe;
import homework.homework3_2.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/recipe")
@RestController
public class RecipeController {
    private final RecipeService recipeService;
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity<Recipe> addIngredient(@RequestBody Recipe recipe){
        Recipe addedRecipe = recipeService.addRecipe(recipe);
        return ResponseEntity.ok(addedRecipe);
    }
    @GetMapping("{id}")
    public ResponseEntity<Recipe> getIngredient(@PathVariable int id){
        Recipe recipe = recipeService.getRecipe(id);
        if(recipe == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }
    @PutMapping("{id}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable int id, @RequestBody Recipe recipe){
        Recipe recipe1 = recipeService.editRecipe(id, recipe);
        if(recipe1 == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe1);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable int id){
        if(recipeService.deleteRecipe(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping
    public ResponseEntity<Map<Integer, Recipe>> getAllRecipes(){
        Map<Integer, Recipe> recipes = recipeService.getAllRecipes();
        if(recipes == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipes);
    }
}
