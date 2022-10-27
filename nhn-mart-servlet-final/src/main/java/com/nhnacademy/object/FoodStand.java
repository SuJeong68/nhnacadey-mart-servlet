package com.nhnacademy.object;

import java.util.ArrayList;

public class FoodStand {
    private ArrayList<Food> foods = new ArrayList<>();

    public void add(Food onion1) {
        foods.add(onion1);
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }
}
