package homework.homework3_2.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import homework.homework3_2.model.Ingredient;
import homework.homework3_2.services.FileService;
import homework.homework3_2.services.IngredientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientServiceImpl implements IngredientService {
    @Value("${name.of.ingredients.data.file}")
    private String dataFileName;
    private final FileService fileService;
    private int lastId = 0;
    private TreeMap<Integer, Ingredient> ingredients = new TreeMap<>();

    public IngredientServiceImpl(FileService fileService) {
        this.fileService = fileService;
    }
    @PostConstruct
    private void init(){
        readFromFile();
    }

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        ingredients.put(lastId++, ingredient);
        saveToFile();
        return ingredient;
    }

    @Override
    public Ingredient getIngredient(int id) {
        Ingredient ingredient = ingredients.get(id);
        if (ingredient != null) {
            return ingredient;
        }
        return null;
    }

    @Override
    public Ingredient editIngredient(int id, Ingredient ingredient) {
        if (ingredients.containsKey(id)) {
            ingredients.put(id, ingredient);
            saveToFile();
            return ingredient;
        }
        return null;
    }

    @Override
    public boolean deleteIngredient(int id) {
        if (ingredients.containsKey(id)) {
            ingredients.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public TreeMap<Integer, Ingredient> getAllIngredients() {
        return ingredients;
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredients);
            fileService.saveToFile(json, dataFileName);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        String json = fileService.readFromFile(dataFileName);
        try {
            ingredients = new ObjectMapper().readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
