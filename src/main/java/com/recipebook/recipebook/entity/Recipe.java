package com.recipebook.recipebook.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "recipe_book")
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @Column(name = "recipe_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "recipe_name")
    private String name;

    @Column(name = "instruction")
    private String instruction;

    @Column(name = "is_veg")
    private Boolean isVeg = true;

    @Column(name = "servings")
    private Integer servings;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "recipe_id_fk") // Use a foreign key column in Ingredients table to reference Recipe
    private List<Ingredients> ingredients = new ArrayList<>();


}
