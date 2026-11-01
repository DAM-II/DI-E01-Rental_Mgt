package com.samuel.entrega_01.models;

public enum GameGenre {
    ACTION("Action"), ADVENTURE("Adventure"), SPORTS("Sports"), RPG("Role Play"), STRATEGY("Strategy"), RACING("Racing"), SIMULATION("Simulation");

    private final String displayName;

    GameGenre(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName(){
        return displayName;
    }

    @Override
    public String toString(){
        return getDisplayName();
    }
}
