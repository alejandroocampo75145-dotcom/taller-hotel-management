package model;

public class SingleRoom extends Room {
    public SingleRoom(int number, int floor, boolean directSeaView, boolean hasPremiumChannels, Hotel hotel) {
        
        super(number, floor, 1, 0, directSeaView, hasPremiumChannels, hotel);
    }

    @Override
    public double calculateBedRate(int guestCount) {
        
        return getHotel().getSingleBedRate();
    }
}

