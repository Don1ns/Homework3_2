package homework.homework3_2.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import homework.homework3_2.model.Ingredient;
import homework.homework3_2.model.Recipe;
import homework.homework3_2.services.FileService;
import homework.homework3_2.services.RecipeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.TreeMap;
@Service
public class RecipeServiceImpl implements RecipeService {
    @Value("${name.of.recipes.data.file}")
    private String dataFileName;
    private final FileService fileService;
    private int lastId = 0;
    private Map<Integer, Recipe> recipes = new TreeMap<>();

    public RecipeServiceImpl(FileService fileService) {
        this.fileService = fileService;
    }
    @PostConstruct
    private void init(){
        readFromFile();
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipes.put(lastId++, recipe);
        saveToFile();
        return  recipe;
    }

    @Override
    public Recipe getRecipe(int id) {
        return recipes.get(id);
    }
    @Override
    public Recipe editRecipe(int id, Recipe recipe){
        if (recipes.containsKey(id)){
            recipes.put(id, recipe);
            saveToFile();
            return recipe;
        }
        return null;
    }
    @Override
    public boolean deleteRecipe(int id){
        if (recipes.containsKey(id)){
            recipes.remove(id);
            saveToFile();
            return true;
        }
        return false;
    }
    @Override
    public Map<Integer, Recipe> getAllRecipes() {
        return recipes;
    }
    @Override
    public Path createRecipesFile() throws IOException {
        Path path = fileService.createTempFile("recipes");
        try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)){
            for (Recipe recipe : recipes.values()) {
                int i = 1;
                writer.append(recipe.getTitle()).append("\n").append("Время приготовления: ").append(String.valueOf(recipe.getCookingTime())).append(" минут.\n").append("Ингредиенты: \n");
                for(Ingredient ingredient : recipe.getIngredients()){
                    writer.append(" · ").append(ingredient.toString()).append("\n");
                }
                writer.append("Инструкция приготовления:\n");
                for(String step : recipe.getCookingSteps()){
                    writer.append(String.valueOf(i++)).append(". ").append(step).append("\n");
                }
            }
        }
        return path;
    }
    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipes);
            fileService.saveToFile(json, dataFileName);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile() {
        String json = fileService.readFromFile(dataFileName);
        try {
            recipes = new ObjectMapper().readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
