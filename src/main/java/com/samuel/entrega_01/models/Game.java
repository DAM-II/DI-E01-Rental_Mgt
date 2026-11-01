package com.samuel.entrega_01.models;

import java.util.Map;

public class Game {
    private String name;
    private GameGenre genre;
    private Map<Platform, Integer> stock;

    public Game(String name, GameGenre genre, Map<Platform, Integer> startingStock) {
        this.name = name;
        this.genre = genre;
        stock = startingStock;
    }

    public GameGenre getGenre(){
        return genre;
    }

    public Integer getUnits(Platform p){
        return stock.getOrDefault(p, 0);
    }

    public boolean isAvailable(Platform p){
        return getUnits(p) >= 1;
    }

    public void incrementStock(Platform p){
        stock.put(p, getUnits(p) + 1);
    }

    public boolean decrementStock(Platform p){
        if(isAvailable(p)){
            stock.put(p, getUnits(p) - 1);
            return true;
        }
        return false;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return getName();
    }

}
