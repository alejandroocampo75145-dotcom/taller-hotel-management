package model;

public class Suite extends Room {
    private boolean hasPrivateLivingRoom;

    public Suite(int number, int floor, boolean directSeaView, Hotel hotel) {
        
        super(number, floor, 0, 1, directSeaView, true, hotel);
        
        
        int totalFloors = hotel.getFloors();
        if (floor < totalFloors - 1 || floor > totalFloors) {
            throw new IllegalArgumentException("Suites can only be located on the top two floors (" + (totalFloors - 1) + " or " + totalFloors + ").");
        }
        
        this.hasPrivateLivingRoom = true; 
    }

    public boolean hasPrivateLivingRoom() {
        return hasPrivateLivingRoom;
    }

    public void setHasPrivateLivingRoom(boolean hasPrivateLivingRoom) {
        this.hasPrivateLivingRoom = hasPrivateLivingRoom;
    }

    @Override
    public double calculateBedRate(int guestCount) {
        
        return getHotel().calculateDoubleBedRateTwoGuest_TCDS2();
    }

    @Override
    public double calculateMinibarRate() {
        
        return getHotel().getMinibarRate();
    }
}

