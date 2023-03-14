package com.example.gordoapi;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Step {

    private static final int NUM_POSITIONS = 6;

    @Id
    private String id;
    private String instruction; // the actual instruction read out
    private int destinationPosition; // destination position
    private String ingredient; // string for ingredient name
    private double amount; // 0.0 amount
    private String unit; // unit for dispense amount
    private boolean ifActionable; // if dispense or not

    public Step(String instruction, int destinationPosition, String ingredient, double amount, String unit, boolean ifActionable) {
        this.instruction = instruction;
        this.destinationPosition = destinationPosition;
        this.ingredient = ingredient;
        this.amount = amount;
        this.unit = unit;
        this.ifActionable = ifActionable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public int getDestinationPosition() {
        return destinationPosition;
    }

    public void setDestinationPosition(int destinationPosition) {
        this.destinationPosition = destinationPosition;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public boolean isIfActionable() {
        return ifActionable;
    }

    public void setIfActionable(boolean ifActionable) {
        this.ifActionable = ifActionable;
    }
}
