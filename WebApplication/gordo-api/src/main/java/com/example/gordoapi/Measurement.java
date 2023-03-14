package com.example.gordoapi;

public class Measurement {
    private int amount;
    private String units;

    public Measurement(int amount, String units) {
        this.amount = amount;
        this.units = units;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public int getAmount() {
        return amount;
    }

    public String getUnits() {
        return units;
    }
}
