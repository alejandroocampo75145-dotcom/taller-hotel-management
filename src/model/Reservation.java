package model;

public class Reservation {
    private int id;
    private Guest guest;
    private Room room;
    private int guestCount;
    private int nightsNumber;
    private int status; // -1: Pending, 0: Confirmed, 1: Cancelled

    public Reservation(int id, Guest guest, Room room, int guestCount, int nightsNumber) {
        setId(id);
        setGuest(guest);
        setRoom(room);
        setGuestCount(guestCount);
        setNightsNumber(nightsNumber);
        this.status = -1; // Toda reserva nace en estado pendiente
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("The reservation ID must be positive");
        }
        this.id = id;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        if (guest == null) {
            throw new IllegalArgumentException("The reservation must have a valid guest");
        }
        this.guest = guest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("The reservation must have a valid room");
        }
        this.room = room;
    }

    public int getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(int guestCount) {
        if (guestCount <= 0) {
            throw new IllegalArgumentException("Guest count must be greater than zero");
        }
        this.guestCount = guestCount;
    }

    public int getNightsNumber() {
        return nightsNumber;
    }

    public void setNightsNumber(int nightsNumber) {
        if (nightsNumber <= 0) {
            throw new IllegalArgumentException("Nights count must be greater than zero");
        }
        this.nightsNumber = nightsNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        
        if (status < -1 || status > 1) {
            throw new IllegalArgumentException("Invalid status");
        }
        this.status = status;
    }

    // El cálculo extrae el costo por noche desde la habitación (que internamente hace el polimorfismo)
    public double calculateTotalCost(Hotel hotel) {
        double costPerNight = this.room.calculateCostPerNight(this.guestCount);
        return costPerNight * this.nightsNumber;
    }
}

