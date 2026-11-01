package com.samuel.entrega_01.services;

import com.samuel.entrega_01.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStore {
    private static final DataStore instance = new DataStore();
    private List<User> users;
    private List<Game> games;
    private List<Rental> rentals;
    private static int nextRentalId;

    private DataStore(){
        users = new ArrayList<>();
        games = new ArrayList<>();
        rentals = new ArrayList<>();
        nextRentalId = 1;
        preloadData();
    }

    public static DataStore getInstance(){
        return instance;
    }

    private void preloadData(){
        User defUser1 = new User("Laura", "García", "", "", "");
        User defUser2 = new User("Marc", "López", "", "", "");
        users.add(defUser1);
        users.add(defUser2);

        Map<Platform, Integer> stock1 = new HashMap<>();
        stock1.put(Platform.PS5, 2);
        stock1.put(Platform.SWITCH_2, 3);
        Game defGame1 = new Game("The Legend of Zelda", GameGenre.ADVENTURE, stock1);

        Map<Platform, Integer> stock2 = new HashMap<>();
        stock2.put(Platform.XBOX, 2);
        stock2.put(Platform.PC, 4);
        Game defGame2 = new Game("Cyberpunk 2077", GameGenre.ACTION, stock2);

        games.add(defGame1);
        games.add(defGame2);

        createRental(defGame1, defUser1, Platform.PS5);
        createRental(defGame2, defUser2, Platform.PC);
    }

    // User ops
    public List<User> getUsers() {
        return users;
    }

    public boolean addUser(User user){
        if(users.contains(user)){
            return false;
        }
        users.add(user);
        return true;
    }

    private boolean canDeleteUser(User user){
        return !userHasActiveRental(user);
    }

    private boolean userHasActiveRental(User user){
        for(Rental r : rentals){
            // If the renter matches AND the game hasn't been returned yet
            if(r.getRenter().equals(user) && r.getActualReturnDate() == null){
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(User user){
        if(canDeleteUser(user)){
            users.remove(user);
            return true;
        }
        return false;
    }

    // Game ops
    public List<Game> getGames() {
        return games;
    }

    public boolean addGame(Game game){
        if(games.contains(game)){
            return false;
        }
        games.add(game);
        return true;
    }

    private boolean canDeleteGame(Game game){
        return !gameHasActiveRental(game);
    }

    private boolean gameHasActiveRental(Game game){
        for(Rental r : rentals){
            if(r.getGameRented().equals(game) && r.getActualReturnDate() == null){
                return true;
            }
        }
        return false;
    }

    public boolean deleteGame(Game game){// or id?
        if(canDeleteGame(game)){
            games.remove(game);
            return true;
        }
        return false;
    }

    // Rental ops
    public List<Rental> getRentals() {
        return rentals;
    }

    public int getNumRentals(){
        return rentals.size();
    }

    public boolean createRental(Game game, User user, Platform platform){
        if(game.isAvailable(platform)){
            game.decrementStock(platform);
            Rental newRental = new Rental(nextRentalId++, game, user, platform);
            rentals.add(newRental);
            return true;
        }
        return false;
    }

    public boolean returnRental(Rental rental){
        if(rental != null && rental.getActualReturnDate() == null){
            rental.returnRental();
            return true;
        }
        return false; // Already returned, protects against double-returns
    }
}
