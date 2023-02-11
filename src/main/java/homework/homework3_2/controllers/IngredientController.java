package homework.homework3_2.controllers;

import homework.homework3_2.model.Ingredient;
import homework.homework3_2.services.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/ingredient")
@RestController
@Tag(name = "Ингредиенты",
        description = "Операции для работы с ингредиентами")
public class IngredientController {
    private final IngredientService ingredientService;
    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    @Operation(
            summary = "Добавление нового ингредиента"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ингредиент добавлен"
    )
    public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingredient){
        Ingredient addedIngredient = ingredientService.addIngredient(ingredient);
        return ResponseEntity.ok(addedIngredient);
    }
    @GetMapping("{id}")
    @Operation(
            summary = "Получение ингредиента по id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ингредиент получен"
    )
    public ResponseEntity<Ingredient> getIngredient(@PathVariable int id){
        Ingredient ingredient = ingredientService.getIngredient(id);
        if(ingredient == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient);
    }
    @PutMapping("{id}")
    @Operation(
            summary = "Редактирование ингредиента по id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ингредиент отредактирован"
    )
    public ResponseEntity<Ingredient> editIngredient(@PathVariable int id, @RequestBody Ingredient ingredient){
        Ingredient ingredient1 = ingredientService.editIngredient(id, ingredient);
        if(ingredient1 == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ingredient1);
    }
    @DeleteMapping("{id}")
    @Operation(
            summary = "Удаление ингредиента по id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ингредиент удален"
    )
    public ResponseEntity<Void> deleteIngredient(@PathVariable int id){
        if(ingredientService.deleteIngredient(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/")
    @Operation(
            summary = "Получение всех ингредиентов"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ингредиенты получены"
    )
    public ResponseEntity<Map<Integer, Ingredient>> getAllIngredients(){
        Map<Integer, Ingredient> ingredients = ingredientService.getAllIngredients();
        return ResponseEntity.ok(ingredients);
    }
}
