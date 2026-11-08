package model;

public class FamilyRoom extends Room {
    public FamilyRoom(int number, int floor, int singleBedCount, boolean directSeaView, boolean hasPremiumChannels, Hotel hotel) {
        // Se inyecta la cantidad de camas sencillas que pida el usuario, y 2 dobles fijas
        super(number, floor, singleBedCount, 2, directSeaView, hasPremiumChannels, hotel);
        
        // Validación estricta del negocio: entre 2 y 4 camas sencillas
        if (singleBedCount < 2 || singleBedCount > 4) {
            throw new IllegalArgumentException("A family room must have between 2 and 4 single beds.");
        }
    }

    @Override
    public double calculateBedRate(int guestCount) {
        // Tarifa mínima garantizada por negocio: 2 dobles ocupadas por 2 + 2 sencillas
        double baseBedRate = (2 * getHotel().calculateDoubleBedRateTwoGuest_TCDS2()) + 
                             (2 * getHotel().getSingleBedRate());
        
        // Excedente si hay más de 6 huéspedes (se cobra 70% de TCS por cada extra)
        if (guestCount > 6) {
            int extraGuests = guestCount - 6;
            baseBedRate += (extraGuests * getHotel().calculateAdditionalPersonRate_TPA());
        }
        
        return baseBedRate;
    }
}

