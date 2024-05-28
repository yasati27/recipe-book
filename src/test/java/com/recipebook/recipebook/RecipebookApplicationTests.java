package com.recipebook.recipebook;

import com.recipebook.recipebook.dao.RecipeDao;
import com.recipebook.recipebook.entity.Ingredients;
import com.recipebook.recipebook.entity.Recipe;
import com.recipebook.recipebook.service.RecipeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class RecipebookApplicationTests {

	@Autowired
	private RecipeDao recipeDao;

	@Autowired
	private RecipeService recipeService;


	@Test
	public void test_findFilteredRecipe() {

		List<Recipe> mockRecipes = new ArrayList<>();
		Recipe r1 = new Recipe(null, "Recipe 1", "Instruction 1", true, 1, Arrays.asList(new Ingredients(null,"ING2"),new Ingredients(null,"ING1")));
		Recipe r2 = new Recipe(null, "Recipe 2", "Instruction 2", false, 2, Arrays.asList(new Ingredients(null,"ING3"),new Ingredients(null,"ING2")));

		// Saving method of recipeDao
		recipeDao.save(r1);
		recipeDao.save(r2);
		// Call the service method
		List<Recipe> result = recipeService.findRecipes(true,null,"ING2",null,null);
		List<Recipe> expected = List.of(new Recipe(null, "Recipe 1", "Instruction 1", true, 1, Arrays.asList(new Ingredients(null,"ING2"),new Ingredients(null,"ING1"))));

		List<String> expectedList = expected.get(0).getIngredients().stream().map(Ingredients::getName).sorted().toList();
		List<String> resultList = result.get(0).getIngredients().stream().map(Ingredients::getName).sorted().toList();

		//assert
		Assertions.assertEquals(expected.get(0).getName(),result.get(0).getName());
		Assertions.assertEquals(expected.get(0).getIsVeg(),result.get(0).getIsVeg());
		Assertions.assertTrue(resultList.containsAll(expectedList));

		//Call delete methods
		recipeDao.delete(r1);
		recipeDao.delete(r2);



	}

}
