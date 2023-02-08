package homework.homework3_2.controllers;

import homework.homework3_2.model.Ingredient;
import homework.homework3_2.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/ingredient")
@RestController
public class IngredientController {
    private final IngredientService ingredientService;
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingredient){
        Ingredient addedIngredient = ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok(addedIngredient);
    }
    @GetMapping("{id}")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable int id){
        Ingredient ingredient = ingredientService.getIngredient(id);
        if(ingredient == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }
    @PutMapping("{id}")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable int id, @RequestBody Ingredient ingredient){
        Ingredient ingredient1 = ingredientService.editIngredient(id, ingredient);
        if(ingredient1 == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient1);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable int id){
        if(ingredientService.deleteIngredient(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/all")
    public ResponseEntity<Map<Integer, Ingredient>> getAllIngredients(){
        Map<Integer, Ingredient> ingredients = ingredientService.getAllIngredients();
        if(ingredients == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredients);
    }
}
