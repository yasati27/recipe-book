package com.recipebook.recipebook.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "ingredients")
@NoArgsConstructor
@AllArgsConstructor
public class Ingredients {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ingredient_id")
        private Long id;

        @Column(name = "ingredient_name")
        private String name;


 }
