package com.example.gordoapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UIController {

    @Autowired
    private RecipeRepository repository;

    @GetMapping("/Recipe/get/byName/{name}")
    public Recipe getRecipeByName(@PathVariable String name) {
        return repository.findItemByName(name);
    }

    @GetMapping("/Recipe/get/{id}")
    public Recipe getRecipe(@PathVariable String id) {
        if (repository.existsById(id)){
            return repository.findById(id).get(); // should never be null
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
    }

    @GetMapping("/Recipe/getAll")
    public List<Recipe> getRecipeAll() {
        List<Recipe> opt = repository.findAll();
        return opt;
    }

    @PostMapping("/Recipe/populateRecipes")
    public List<Recipe> populateRecipes() {
        repository.deleteAll();
        List<Recipe> list = new ArrayList<>();

        List<String> contents = new ArrayList<>();
        contents.add("salt");
        contents.add("pepper");
        contents.add("paprika");
        contents.add("n/a");
        contents.add("water");
        contents.add("n/a");

        List<Step> steps = new ArrayList<>();
        steps.add(new Step("step 1", 0, "salt", 5, "grams", true));
        steps.add(new Step("step 2", 1, "pepper", 6, "grams", true));
        steps.add(new Step("step 3", 2, "paprika", 7, "grams", true));
        steps.add(new Step("step 4", 0, "potatoes", 0, "pieces", false));
        steps.add(new Step("step 5", 4, "water", 9, "mL", true));
        Recipe rec = new Recipe("test", "public", 5, false, steps, contents);
        list.add(rec);
        repository.save(rec);

        List<String> contents1 = new ArrayList<>();
        contents1.add("salt");
        contents1.add("pepper");
        contents1.add("garlic");
        contents1.add("oregano");
        contents1.add("water");
        contents1.add("milk");

        List<Step> steps1 = new ArrayList<>();
        steps1.add(new Step("Mince the garlic and slice the mushrooms.", 0, "garlic and mushrooms", 0, "pieces", false));
        steps1.add(new Step("Add the butter and dispense garlic to a deep skillet and sauté over medium heat for one minute.", 2, "garlic", 6, "grams", true));
        steps1.add(new Step("Grill the chicken in the skillet", 0, "chicken", 0, "pieces", false));
        steps1.add(new Step("Add milk / heavy cream to skillet", 5, "milk", 5, "grams", true));
        steps1.add(new Step("Salt the Chicken", 0, "salt", 1, "grams", true));
        steps1.add(new Step("Pepper the Chicken", 1, "pepper", 2, "grams", true));
        steps1.add(new Step("Boil the pasta, and add the chicken to the boiled pasta.", 0, "water", 10, "mL", true));
        steps1.add(new Step("Season with oregano", 0, "oregano", 2, "grams", true));
        Recipe rec1 = new Recipe("Garlic Chicken Pasta", "public", 15, false, steps1, contents1);
        list.add(rec1);
        repository.save(rec1);

        List<Step> steps2 = new ArrayList<>();
        steps2.add(new Step("step 1", 0, "salt", 5, "grams", true));
        steps2.add(new Step("step 2", 1, "pepper", 6, "grams", true));
        steps2.add(new Step("step 3", 2, "paprika", 7, "grams", true));
        steps2.add(new Step("step 4", 0, "potatoes", 8, "pieces", false));
        steps2.add(new Step("step 5", 4, "water", 9, "mL", true));
        Recipe rec2 = new Recipe("test2", "public", 5, false, steps2, contents);
        list.add(rec2);
        repository.save(rec2);

        List<Step> steps3 = new ArrayList<>();
        steps3.add(new Step("step 1", 0, "salt", 5, "grams", true));
        steps3.add(new Step("step 2", 1, "pepper", 6, "grams", true));
        steps3.add(new Step("step 3", 2, "paprika", 7, "grams", true));
        steps3.add(new Step("step 4", 0, "potatoes", 8, "pieces", false));
        steps3.add(new Step("step 5", 4, "water", 9, "mL", true));
        Recipe rec3 = new Recipe("test3", "public", 5, false, steps3, contents);
        list.add(rec3);
        repository.save(rec3);

        return list;
    }

    // example curl -i -X POST -H "Content-Type: application/json" -d '{"id":"6266c7fd3bca9b6083d8f10c","name":"test2","instructions":["step 1","step 2","step 3"],"dispenseList":[{"amount":1,"units":"mL"},{"amount":2,"units":"g"},{"amount":3,"units":"mL"}]}' http://localhost:8080/Recipe/add
    // id will be reassigned
    @PostMapping("/Recipe/add")
    public Recipe addRecipe(@RequestBody Recipe rec) {
        Recipe rnew = new Recipe(rec.getName(), rec.getOwnerUsername(), rec.getApproximateTime(), rec.isPrivateRecipe(), rec.getSteps(), rec.getSetupContents());
        repository.save(rnew);
        return rnew;
    }

    @PutMapping("/Recipe/update/{id}")
    public void updateRecipe(@PathVariable String id, @RequestBody Recipe rec) {
        if (repository.existsById(id)){
            Recipe r = repository.findById(id).get(); // should never be null
            r.update(rec);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
    }

    @DeleteMapping("/Recipe/delete/{id}")
    public Recipe deleteRecipe(@PathVariable String id) {
        if (repository.existsById(id)){
            Recipe r = repository.findById(id).get(); // should never be null
            repository.delete(r);
            return r;
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        }
    }
}
