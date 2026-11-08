package model;

public class DoubleRoom extends Room {
    public DoubleRoom(int number, int floor, boolean directSeaView, boolean hasPremiumChannels, Hotel hotel) {
        // Se inyecta hardcodeado: 0 camas sencillas, 1 cama doble
        super(number, floor, 0, 1, directSeaView, hasPremiumChannels, hotel);
    }

    @Override
    public double calculateBedRate(int guestCount) {
        if (guestCount == 1) {
            return getHotel().calculateDoubleBedRateOneGuest_TCD1();
        }
        return getHotel().calculateDoubleBedRateTwoGuest_TCDS2();
    }
}

