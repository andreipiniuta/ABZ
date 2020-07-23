package sample.service;

import sample.Material;
import sample.Recipe;

import java.io.IOException;
import java.util.List;

public interface RecipeService {

    public  void saveRecipe(Recipe recipe) throws IOException;

    List<Recipe> getAllRecipe() throws IOException;

    Recipe getRecipeByID(Integer ID) throws IOException;

    public void deleteRecipe (Integer ID) throws IOException;
}
