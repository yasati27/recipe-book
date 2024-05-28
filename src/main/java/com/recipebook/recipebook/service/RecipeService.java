package com.recipebook.recipebook.service;

import com.recipebook.recipebook.entity.Recipe;

import java.util.List;

public interface RecipeService {

List<Recipe> getAllRecipes();

Recipe getRecipesById(Long id);

Recipe addRecipe(Recipe recipe);

Recipe updateRecipe(Recipe recipe);

void deleteRecipe(Long id);

List<Recipe> findRecipes(Boolean vegetarian, Integer servings, String ingredientInclude, String ingredientExclude, String keyword);
}
