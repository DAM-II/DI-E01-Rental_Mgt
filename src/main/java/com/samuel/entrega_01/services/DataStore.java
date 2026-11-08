package com.samuel.entrega_01.services;

import com.samuel.entrega_01.models.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStore {
    private static final DataStore instance = new DataStore();
    private final List<User> users;
    private final List<Game> games;
    private final List<Rental> rentals;
    private static int nextRentalId;

    private DataStore(){
        users = new ArrayList<>();
        games = new ArrayList<>();
        rentals = new ArrayList<>();
        nextRentalId = 1;
    }

    public static DataStore getInstance(){
        return instance;
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

    public boolean canDeleteUser(User user){
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

    public boolean canDeleteGame(Game game){
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

    public List<Rental> getActiveRentals() {
        List<Rental> active = new ArrayList<>();

        for(Rental r : rentals){
            if(r.getActualReturnDate() == null)
                active.add(r);
        }
        return active;
    }

    // Test data
    public boolean loadSampleData(){
        if(sampleDataAlreadyExists())
            return false;

        User user1 = new User("Laura", "García", "Martínez", "12345678A", "600112233");
        User user2 = new User("Marc", "López", "Sánchez", "87654321B", "600445566");
        User user3 = new User("Anna", "Soler", "Pujol", "45678912C", "622334455");
        users.add(user1);
        users.add(user2);
        users.add(user3);

        Map<Platform, Integer> stock1 = new HashMap<>();
        stock1.put(Platform.PS5, 1);
        stock1.put(Platform.SWITCH_2, 3);
        Game game1 = new Game("The Legend of Zelda", GameGenre.ADVENTURE, stock1);

        Map<Platform, Integer> stock2 = new HashMap<>();
        stock2.put(Platform.PC, 4);
        stock2.put(Platform.PS5, 1);
        stock2.put(Platform.XBOX, 2);
        Game game2 = new Game("Cyberpunk 2077", GameGenre.ACTION, stock2);

        Map<Platform, Integer> stock3 = new HashMap<>();
        stock3.put(Platform.PC, 1);
        stock3.put(Platform.PS5, 2);
        stock3.put(Platform.XBOX, 1);
        Game game3 = new Game("FIFA 25", GameGenre.SPORTS, stock3);

        games.add(game1);
        games.add(game2);
        games.add(game3);

        LocalDate r1Start = LocalDate.now().minusDays(14);
        rentals.add(new Rental(1, game1, user1, Platform.SWITCH_2, r1Start, r1Start.plusDays(7), r1Start.plusDays(5)));

        LocalDate r2Start = LocalDate.now().minusDays(20);
        rentals.add(new Rental(2, game2, user2, Platform.PC, r2Start, r2Start.plusDays(7), r2Start.plusDays(10)));

        LocalDate r3Start = LocalDate.now().minusDays(15);
        rentals.add(new Rental(3, game3, user3, Platform.PS5, r3Start, r3Start.plusDays(7), null));
        game3.decrementStock(Platform.PS5);

        LocalDate r4Start = LocalDate.now().minusDays(2);
        rentals.add(new Rental(4, game2, user1, Platform.XBOX, r4Start, r4Start.plusDays(7), null));
        game2.decrementStock(Platform.XBOX);

        nextRentalId = 5;
        return true;
    }


    private boolean sampleDataAlreadyExists(){
        return !users.isEmpty() || !games.isEmpty() || !rentals.isEmpty();
    }
}
