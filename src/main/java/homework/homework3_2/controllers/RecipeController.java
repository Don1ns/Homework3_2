package homework.homework3_2.controllers;

import homework.homework3_2.model.Recipe;
import homework.homework3_2.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@RequestMapping("/recipe")
@RestController
@Tag(name = "Рецепты",
        description = "Операции для работы с рецептами")
public class RecipeController {
    private final RecipeService recipeService;
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    @Operation(
            summary = "Добавление нового рецепта"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Рецепт добавлен"
    )
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe){
        Recipe addedRecipe = recipeService.addRecipe(recipe);
        return ResponseEntity.ok(addedRecipe);
    }
    @GetMapping("{id}")
    @Operation(
            summary = "Получение рецепта по id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт получен"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Рецепт не найден")
    })
    public ResponseEntity<Recipe> getRecipe(@PathVariable int id){
        Recipe recipe = recipeService.getRecipe(id);
        if(recipe == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe);
    }
    @PutMapping("{id}")
    @Operation(
            summary = "Редактирование рецепта по id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт отредактирован"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Рецепт не найден")
    })
    public ResponseEntity<Recipe> editRecipe(@PathVariable int id, @RequestBody Recipe recipe){
        Recipe recipe1 = recipeService.editRecipe(id, recipe);
        if(recipe1 == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recipe1);
    }
    @DeleteMapping("{id}")
    @Operation(
            summary = "Удаление рецепта по id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт удален"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Рецепт не найден")
    })
    public ResponseEntity<Void> deleteRecipe(@PathVariable int id){
        if(recipeService.deleteRecipe(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/")
    @Operation(
            summary = "Получение всех рецептов"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Рецепты получены"
    )
    public ResponseEntity<Map<Integer, Recipe>> getAllRecipes(){
        Map<Integer, Recipe> recipes = recipeService.getAllRecipes();
        return ResponseEntity.ok(recipes);
    }
    @GetMapping("/export")
    @Operation(
            summary = "Получение всех рецептов в виде txt-файла"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл получен"
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "Нет контента"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Что-то пошло не так")
    })
    public ResponseEntity<Object> downloadAllRecipes(){
        try {
            Path path = recipeService.createRecipesFile();
            if(Files.size(path) == 0){
                return ResponseEntity.noContent().build();
            }
            InputStreamResource resource = new InputStreamResource(new FileInputStream(path.toFile()));
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(Files.size(path))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"recipes.txt\"")
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.toString());
        }

    }
}
