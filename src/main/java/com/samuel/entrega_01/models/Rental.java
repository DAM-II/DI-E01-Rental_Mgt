package com.samuel.entrega_01.models;

import java.time.LocalDate;

public class Rental {
    private static final int standardRentInterval = 7;
    private int registryNumber;
    private Game gameRented;
    private User renter;
    private Platform gamePlatform;
    private LocalDate startDate;
    private LocalDate expectedReturnDate;
    private LocalDate actualReturnDate;

    public Rental(int registryNumber, Game gameRented, User renter, Platform gamePlatform) {
        this.registryNumber = registryNumber;
        this.gameRented = gameRented;
        this.renter = renter;
        this.gamePlatform = gamePlatform;
        this.startDate = LocalDate.now();
        this.expectedReturnDate = startDate.plusDays(standardRentInterval);
        this.actualReturnDate = null;
    }

    public void returnRental(){
        actualReturnDate = LocalDate.now();
        this.gameRented.incrementStock(this.getGamePlatform());
    }

    public RentalStatus getStatus(){
        return calcStatus();
    }

    private RentalStatus calcStatus(){
        // 1. If it hasn't been returned, and it's still before deadline
        if(actualReturnDate == null && isWithinDeadline()){
            return RentalStatus.ACTIVE;
        }
        // 2. If it hasn't been returned BUT it's late
        if(actualReturnDate == null && !isWithinDeadline()){
            return RentalStatus.OVERDUE;
        }
        // 3. If it was returned on time
        if(!actualReturnDate.isAfter(expectedReturnDate)){
            return RentalStatus.EARLY_RETURN;
        }
        // 4. If it was returned late
        return RentalStatus.LATE_RETURN;
    }

    private boolean isWithinDeadline(){
        return !LocalDate.now().isAfter(expectedReturnDate);
    }

    public int getRegistryNumber() {
        return registryNumber;
    }

    public Game getGameRented() {
        return gameRented;
    }

    public User getRenter() {
        return renter;
    }

    public Platform getGamePlatform() {
        return gamePlatform;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }
}
