package homework.homework3_2.model;

import java.util.List;
import java.util.Objects;

public class Recipe {
    private String title;
    private int cookingTime;
    private List<Ingredient> ingredients;
    private List<String> cookingSteps;

    public Recipe(String title, int cookingTime, List<Ingredient> ingredients, List<String> cookingSteps) {
        setTitle(title);
        setCookingTime(cookingTime);
        this.ingredients = ingredients;
        this.cookingSteps = cookingSteps;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title != null && !title.isEmpty()){
            this.title = title;
        }
        else{
            this.title = "default";
        }
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        if(cookingTime > 0){
            this.cookingTime = cookingTime;
        }
        else{
            this.cookingTime = 1;
        }
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        if(!ingredients.isEmpty()){
            this.ingredients = ingredients;
        }
    }

    public List<String> getCookingSteps() {
        return cookingSteps;
    }

    public void setCookingSteps(List<String> cookingSteps) {
        if(!cookingSteps.isEmpty()){
            this.cookingSteps = cookingSteps;
        }
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "title='" + title + '\'' +
                ", cookingTime=" + cookingTime +
                ", ingredients=" + ingredients +
                ", cookingSteps=" + cookingSteps +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return cookingTime == recipe.cookingTime && Objects.equals(title, recipe.title) && Objects.equals(ingredients, recipe.ingredients) && Objects.equals(cookingSteps, recipe.cookingSteps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, cookingTime, ingredients, cookingSteps);
    }
}
