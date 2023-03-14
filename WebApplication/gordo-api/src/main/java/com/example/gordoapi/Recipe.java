package com.example.gordoapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Recipe {

    @Id
    @JsonProperty
    private String id;

    private String name; // recipe name

    private String ownerUsername;

    private double approximateTime; // in minutes

    private boolean isPrivateRecipe; // flag for private to this user

    private List<Step> steps;

    private List<String> setupContents;

    public Recipe() {
        this.name = "";
        this.ownerUsername = "public";
        this.approximateTime = 0;
        this.isPrivateRecipe = false;
        this.steps = new ArrayList<>();
        this.setupContents = new ArrayList<>();
    }

    public Recipe(String name, String ownerUsername, double approximateTime, boolean isPrivateRecipe, List<Step> steps, List<String> setupContents) {
        this.name = name;
        this.ownerUsername = ownerUsername;
        this.approximateTime = approximateTime;
        this.isPrivateRecipe = isPrivateRecipe;
        this.steps = steps;
        this.setupContents = setupContents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public double getApproximateTime() {
        return approximateTime;
    }

    public void setApproximateTime(double approximateTime) {
        this.approximateTime = approximateTime;
    }

    public boolean isPrivateRecipe() {
        return isPrivateRecipe;
    }

    public void setPrivateRecipe(boolean privateRecipe) {
        isPrivateRecipe = privateRecipe;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public List<String> getSetupContents() {
        return setupContents;
    }

    public void setSetupContents(List<String> setupContents) {
        this.setupContents = setupContents;
    }

    public void update(Recipe newRec){ // keeps id of original
        this.name = name;
        this.ownerUsername = ownerUsername;
        this.approximateTime = approximateTime;
        this.isPrivateRecipe = isPrivateRecipe;
        this.steps = steps;
        this.setupContents = newRec.setupContents;
    }
}
