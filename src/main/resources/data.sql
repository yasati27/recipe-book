-- Insert data into recipe_book table
INSERT INTO recipe_book (recipe_name, instruction, is_veg, servings) VALUES
('Classic Margherita Pizza', '1. Roll out pizza dough. 2. Spread tomato sauce. 3. Add mozzarella and basil. 4. Bake until golden.', TRUE, 4),
('Chicken Caesar Salad', '1. Grill chicken. 2. Toss lettuce with dressing. 3. Top with grilled chicken, croutons, and Parmesan.', FALSE, 2),
('Noodles', '1. Boil Noodles. 2. . 3. Top with grilled vegetable of your choice like, beans, and carrot and add soye sauce', TRUE, 3);

-- Insert data into ingredients table
INSERT INTO ingredients (ingredient_name, recipe_id_fk) VALUES
('Pizza dough', 1),
('Tomato sauce', 1),
('Mozzarella', 1),
('Fresh basil', 1),
('Chicken breasts', 2),
('Romaine lettuce', 2),
('Caesar dressing', 2),
('Croutons', 2),
('Noodles', 3),
('Soye Sauce', 3),
('Vegetable of choice', 3);
