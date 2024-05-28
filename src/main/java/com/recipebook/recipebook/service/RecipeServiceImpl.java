package com.recipebook.recipebook.service;

import com.recipebook.recipebook.controller.RecipeController;
import com.recipebook.recipebook.dao.RecipeDao;
import com.recipebook.recipebook.entity.Recipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService{

    Logger logger = LoggerFactory.getLogger(RecipeServiceImpl.class);

    @Autowired
    private RecipeDao recipeDao;


    @Override
    public List<Recipe> getAllRecipes() {
        try {
            return recipeDao.findAll();
        } catch (Exception e) {
            logger.info("Recipes not found");
        }
        return null;
    }

    public Recipe getRecipesById(Long id){
        try {
            Optional<Recipe> getbyId = recipeDao.findById(id);
            return getbyId.orElse(null);
        } catch (Exception e) {
            logger.info("Recipe could not be found for id:" +id);
        }
        return null;
    }


    @Override
    public Recipe addRecipe(Recipe recipe) {
        try {
            return recipeDao.save(recipe);
        } catch (Exception e) {
            logger.info("Recipe could not be added");
        }
        return null;
    }

    @Override
    public Recipe updateRecipe(Recipe recipe) {
        try {
            return recipeDao.save(recipe);
        } catch (Exception e) {
            logger.info("Recipe could not be updated");
        }
        return recipe;
    }


    @Override
    public void deleteRecipe(Long id) {
        try {
            recipeDao.deleteById(id);
        } catch (Exception e) {
            logger.info("Recipe could not be deleted");
        }
    }

    @Override
    public List<Recipe> findRecipes(Boolean vegetarian, Integer servings, String ingredientInclude, String ingredientExclude, String keyword) {
        try {
            return recipeDao.findFilteredRecipe(vegetarian, servings, ingredientInclude, ingredientExclude, keyword);
        } catch (Exception e) {
            logger.info("Recipe could not be found");
        }
        return null;
    }
}
