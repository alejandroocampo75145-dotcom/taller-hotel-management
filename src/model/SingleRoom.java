package model;

public class SingleRoom extends Room {
    public SingleRoom(int number, int floor, boolean directSeaView, boolean hasPremiumChannels, Hotel hotel) {
        // Se inyecta hardcodeado: 1 cama sencilla, 0 camas dobles
        super(number, floor, 1, 0, directSeaView, hasPremiumChannels, hotel);
    }

    @Override
    public double calculateBedRate(int guestCount) {
        // Cobra exclusivamente la tarifa de cama sencilla (TCS)
        return getHotel().getSingleBedRate();
    }
}

