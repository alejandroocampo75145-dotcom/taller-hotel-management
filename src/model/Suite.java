package model;

public class Suite extends Room {
    private boolean hasPrivateLivingRoom;

    public Suite(int number, int floor, boolean directSeaView, Hotel hotel) {
        // 0 sencillas, 1 doble fija. Siempre tiene TV Premium (true).
        super(number, floor, 0, 1, directSeaView, true, hotel);
        
        // Validación de piso: Solo puede estar en los dos pisos superiores
        int totalFloors = hotel.getFloors();
        if (floor < totalFloors - 1 || floor > totalFloors) {
            throw new IllegalArgumentException("Suites can only be located on the top two floors (" + (totalFloors - 1) + " or " + totalFloors + ").");
        }
        
        this.hasPrivateLivingRoom = true; // Obligatorio por reglas de negocio
    }

    public boolean hasPrivateLivingRoom() {
        return hasPrivateLivingRoom;
    }

    public void setHasPrivateLivingRoom(boolean hasPrivateLivingRoom) {
        this.hasPrivateLivingRoom = hasPrivateLivingRoom;
    }

    @Override
    public double calculateBedRate(int guestCount) {
        // La suite SIEMPRE factura la cama doble como si estuviera ocupada por 2 personas
        return getHotel().calculateDoubleBedRateTwoGuest_TCDS2();
    }

    @Override
    public double calculateMinibarRate() {
        // Sobrescribe el 0.0 de la clase padre
        return getHotel().getMinibarRate();
    }
}

