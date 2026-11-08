package model;

public class FamilyRoom extends Room {
    public FamilyRoom(int number, int floor, int singleBedCount, boolean directSeaView, boolean hasPremiumChannels, Hotel hotel) {
        
        super(number, floor, singleBedCount, 2, directSeaView, hasPremiumChannels, hotel);
        
        
        if (singleBedCount < 2 || singleBedCount > 4) {
            throw new IllegalArgumentException("A family room must have between 2 and 4 single beds.");
        }
    }

    @Override
    public double calculateBedRate(int guestCount) {
        // Tarifa mínima garantizada por negocio: 2 dobles ocupadas por 2 + 2 sencillas
        double baseBedRate = (2 * getHotel().calculateDoubleBedRateTwoGuest_TCDS2()) + (2 * getHotel().getSingleBedRate());
        
        
        if (guestCount > 6) {
            int extraGuests = guestCount - 6;
            baseBedRate += (extraGuests * getHotel().calculateAdditionalPersonRate_TPA());
        }
        
        return baseBedRate;
    }
}

