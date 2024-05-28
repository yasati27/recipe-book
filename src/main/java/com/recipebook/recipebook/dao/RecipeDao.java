package com.recipebook.recipebook.dao;

import com.recipebook.recipebook.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeDao extends JpaRepository<Recipe,Long> {

    @Query("SELECT DISTINCT r FROM Recipe r " +
            "LEFT JOIN FETCH r.ingredients i " +
            "WHERE (:vegetarian IS NULL OR r.isVeg = :vegetarian) " +
            "AND (:servings IS NULL OR r.servings = :servings) " +
            "AND (:ingredientInclude IS NULL OR EXISTS (SELECT 1 FROM r.ingredients incl WHERE incl.name = :ingredientInclude)) " +
            "AND (:ingredientExclude IS NULL OR NOT EXISTS (SELECT 1 FROM r.ingredients excl WHERE excl.name = :ingredientExclude)) " +
            "AND (:keyword IS NULL OR r.name LIKE %:keyword% OR r.instruction LIKE %:keyword%)")
    List<Recipe> findFilteredRecipe(@Param("vegetarian") Boolean vegetarian,
                                    @Param("servings") Integer servings,
                                    @Param("ingredientInclude") String ingredientInclude,
                                    @Param("ingredientExclude") String ingredientExclude,
                                    @Param("keyword") String keyword);
}
