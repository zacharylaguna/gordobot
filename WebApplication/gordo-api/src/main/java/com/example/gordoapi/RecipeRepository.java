package com.example.gordoapi;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RecipeRepository extends MongoRepository<Recipe, String> {
    @Query("{name:'?0'}")
    Recipe findItemByName(String name);
}
