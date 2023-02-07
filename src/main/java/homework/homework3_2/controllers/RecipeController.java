package homework.homework3_2.controllers;

import homework.homework3_2.model.Recipe;
import homework.homework3_2.services.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
