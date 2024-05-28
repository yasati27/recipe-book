package com.recipebook.recipebook.controller;

import com.recipebook.recipebook.entity.Recipe;
import com.recipebook.recipebook.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    Logger logger = LoggerFactory.getLogger(RecipeController.class);
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/getRecipe")
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        try {
            List<Recipe> recipes = recipeService.getAllRecipes();
            return new ResponseEntity<>(recipes, HttpStatus.OK);
        } catch (Exception e) {
            logger.info("Recipes not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getRecipe/{id}")
    public ResponseEntity<Recipe> getRecipesById(@PathVariable Long id) {
         try {
            Recipe recipesById = recipeService.getRecipesById(id);
            if(recipesById != null) {
                return new ResponseEntity<>(recipesById, HttpStatus.OK);
            }
        }
         catch (Exception e) {
             logger.info("Recipe not found for given ID:"+id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/addRecipe")
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe){
        try {
            Recipe addRecipe = recipeService.addRecipe(recipe);

            return new ResponseEntity<>(addRecipe, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Recipe could not be added");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateRecipe/{id}")
    public ResponseEntity<Recipe> updateRecipe(@RequestBody Recipe recipe, @PathVariable Long id) {
         try{
             if(!id.equals(recipe.getId())){
                 logger.info("ID mismatch");
                 return new ResponseEntity<>(HttpStatus.BAD_REQUEST);             }
             Recipe updateRecipe = recipeService.updateRecipe(recipe);
             return new ResponseEntity<>(updateRecipe, HttpStatus.OK);
         }
         catch (Exception e){
             logger.error("Recipe could not be updated");
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }


    }
    @DeleteMapping("/deleteRecipe/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable Long id) {
        try{
            if(recipeService.getRecipesById(id) != null) {
                recipeService.deleteRecipe(id);
                return new ResponseEntity<>("Recipe deleted", HttpStatus.OK);
            }
        }
        catch (Exception e) {
            logger.info("Recipe not delete .No recipe found for given ID:"+id);
            return new ResponseEntity<>("No recipe present with this id", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("No recipe present with id:"+id, HttpStatus.NOT_FOUND);

    }

    @GetMapping("/filter")
    public ResponseEntity<List<Recipe>> findRecipes(
            @RequestParam(required = false) Boolean vegetarian,
            @RequestParam(required = false) Integer servings,
            @RequestParam(required = false) String ingredientInclude,
            @RequestParam(required = false) String ingredientExclude,
            @RequestParam(required = false) String keyword
    )  {
        try {
            List<Recipe> recipes = recipeService.findRecipes(vegetarian, servings, ingredientInclude, ingredientExclude, keyword);
           if(!recipes.isEmpty())
            return new ResponseEntity<>(recipes, HttpStatus.OK);
        } catch (RuntimeException e) {
            logger.info("No recipe found for applied filter. Try changing the filter");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("No recipe found for applied filter. Try changing the filter");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
