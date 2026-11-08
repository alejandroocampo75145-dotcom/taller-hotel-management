package model;

public abstract class Room {
    private int number;
    private int floor;
    private int singleBedCount;
    private int doubleBedCount;
    private boolean directSeaView;
    private boolean hasPremiumChannels;
    private boolean enabled;
    private Hotel hotel;

    public Room(int number, int floor, int singleBedCount, int doubleBedCount, boolean directSeaView, boolean hasPremiumChannels, Hotel hotel) {
        if (hotel == null) {
            throw new IllegalArgumentException("The room must belong to a valid hotel");
        }
        this.hotel = hotel; // Se asigna primero porque setFloor lo necesita para validar
        
        setNumber(number);
        setFloor(floor);
        setSingleBedCount(singleBedCount);
        setDoubleBedCount(doubleBedCount);
        setDirectSeaView(directSeaView);
        setHasPremiumChannels(hasPremiumChannels);
        this.enabled = true; // Toda habitación nace habilitada por defecto
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("The room number must be a positive integer");
        }
        this.number = number;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        // Validar que el piso esté entre 1 y el total de pisos del hotel
        if (floor < 1 || floor > hotel.getFloors()) {
            throw new IllegalArgumentException("Invalid floor. The hotel only has " + hotel.getFloors() + " floors.");
        }
        this.floor = floor;
    }

    public int getSingleBedCount() {
        return singleBedCount;
    }

    public void setSingleBedCount(int singleBedCount) {
        if (singleBedCount < 0) {
            throw new IllegalArgumentException("The single bed count cannot be negative");
        }
        this.singleBedCount = singleBedCount;
    }

    public int getDoubleBedCount() {
        return doubleBedCount;
    }

    public void setDoubleBedCount(int doubleBedCount) {
        if (doubleBedCount < 0) {
            throw new IllegalArgumentException("The double bed count cannot be negative");
        }
        this.doubleBedCount = doubleBedCount;
    }

    public boolean hasDirectSeaView() {
        return directSeaView;
    }

    public void setDirectSeaView(boolean directSeaView) {
        this.directSeaView = directSeaView;
    }

    public boolean hasPremiumChannels() {
        return hasPremiumChannels;
    }

    public void setHasPremiumChannels(boolean hasPremiumChannels) {
        this.hasPremiumChannels = hasPremiumChannels;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Hotel getHotel() {
        return hotel;
    }

    

    
    public int getMaximumCapacity() {
        return (this.singleBedCount * 1) + (this.doubleBedCount * 2);
    }

    
    public double calculateSeaViewSurcharge() {
        if (this.directSeaView) {
            return hotel.getBaseRate() * (hotel.getSeaViewPercentage() / 100.0);
        }
        return 0.0;
    }

    
    public double calculateFloorSurcharge() {
        double rvm = calculateSeaViewSurcharge();
        double floorPercentage = hotel.getFloorSurchargePercentage(this.floor);
        return rvm * floorPercentage;
    }

    
    public double calculatePremiumTvSurcharge() {
        if (this.hasPremiumChannels) {
            return hotel.getPremiumTvSurcharge();
        }
        return 0.0;
    }

    
    public double calculateAdjustedBaseRate() {
        double tbh = hotel.getBaseRate();
        double rpi = calculateFloorSurcharge();
        double rvm = calculateSeaViewSurcharge();
        double rcp = calculatePremiumTvSurcharge();
        return tbh + rpi + rvm + rcp;
    }

    
    public double calculateMinibarRate() {
        return 0.0;
    }

    
    public abstract double calculateBedRate(int guestCount);

    
    public double calculateCostPerNight(int guestCount) {
        double tba = calculateAdjustedBaseRate();
        double tca = calculateBedRate(guestCount); 
        double tmi = calculateMinibarRate();      
        
        return tba + tca + tmi;
    }
}

