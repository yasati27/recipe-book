DROP TABLE IF EXISTS ingredients;
DROP TABLE IF EXISTS recipe_book;

CREATE TABLE recipe_book (
                             recipe_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             recipe_name VARCHAR(255) NOT NULL,
                             instruction TEXT,
                             is_veg BOOLEAN DEFAULT TRUE,
                             servings INT
);

CREATE TABLE ingredients (
                             ingredient_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             ingredient_name VARCHAR(255) NOT NULL,
                             recipe_id_fk BIGINT,
                             FOREIGN KEY (recipe_id_fk) REFERENCES recipe_book(recipe_id) ON DELETE CASCADE
);

