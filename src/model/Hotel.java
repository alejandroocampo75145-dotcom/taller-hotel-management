package model;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private int id;
    private String name;
    private String address;
    private int totalFloors;
    private double baseRate;
    private double singleBedRate;
    private double minibarRate;
    private double seaViewPercentaje;
    private double premiumTvSurcharge;
    private List<Room> rooms;
    private List<Reservation> reservations;
    private List<Receipt> receipts;
    private List<Guest> guest;

    public Hotel(int id, String name, String address, int totalFloors, double baseRate, double singleBedRate, double minibarRate, double seaViewPct, double premiumTvfee){
        setId(id);
        setName(name);
        setAddress(address);
        setTotalFloors(totalFloors);
        setBaseRate(baseRate);
        setSingleBedRate(singleBedRate);
        setMinibarRate(minibarRate);
        setSeaViewPercentage(seaViewPct);
        setPremiumTvSurcharge(premiumTvfee);

        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.receipts = new ArrayList<>();
        this.guest = new ArrayList<>();
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        if (id < 0){
            throw new IllegalArgumentException("ID must be a possitive number");
        }
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("The space of name cannot be empty");
        }
        this.name = name;
    }

    public String getAddres(){
        return address;
    }
    public void setAddress(String address){
        
        if(address == null || address.isBlank()){
            throw new IllegalArgumentException("The space of address cannot be empty");
        }
        this.address = address;
    }

    public int getFloors(){
        return totalFloors;
    }
    private void setTotalFloors(int totalFloors){
        if(totalFloors < 8){
            throw new IllegalArgumentException("The hotel must have at least 8 floors");
        }
        this.totalFloors = totalFloors;
    }

    public double getBaseRate() {
        return baseRate;
    }
    public void setBaseRate(double baseRate) {
        if (baseRate <= 0) {
            throw new IllegalArgumentException("The baseFee must be above zero");
        }
        this.baseRate = baseRate;
    }

    public double getSingleBedRate() {
        return singleBedRate;
    }
    public void setSingleBedRate(double singleBedRate) {
        if (singleBedRate <= 0) {
            throw new IllegalArgumentException("The single bed rate must be above zero");
        }
        this.singleBedRate = singleBedRate;
    }

    public double getMinibarRate() {
        return minibarRate;
    }
    public void setMinibarRate(double minibarRate) {
        if (minibarRate <= 0) {
            throw new IllegalArgumentException("The minibar rate must be above zero");
        }
        this.minibarRate = minibarRate;
    }

    public double getSeaViewPercentage() {
        return seaViewPercentaje;
    }
    public void setSeaViewPercentage(double seaViewPercentage) {
        if (seaViewPercentage < 0) {
            throw new IllegalArgumentException("The sea view percentage must be a possitive number");
        }
        this.seaViewPercentaje = seaViewPercentage;
    }

    public double getPremiumTvSurcharge() {
        return premiumTvSurcharge;
    }
    public void setPremiumTvSurcharge(double premiumTvSurcharge) {
        if (premiumTvSurcharge < 0) {
            throw new IllegalArgumentException("The premium tv recharge must be a possitive number");
        }
        this.premiumTvSurcharge = premiumTvSurcharge;
    }

    public List<Room> getRooms() {
        return rooms;
    }
    public void registerRoom(Room roomToAdd) {
        if (roomToAdd == null) {
            throw new IllegalArgumentException("The room cannot be null");
        }
        this.rooms.add(roomToAdd);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
    
    public Reservation createReservation(int id, int guestId, int roomNumber, int guestNumber, int nights) {
        Guest guestFound = null;
        for(Guest g : this.guest){
            if(g.getId() == guestId){
                guestFound = g;
                break;
            }
        }
        
        if(guestFound == null){
            throw new IllegalArgumentException("The guest with the Id " + guestId + " is not registered");
        }    
        
        Room roomFound = null;
        for(Room r : this.rooms){
            if(r.getNumber() == roomNumber){
                roomFound = r;
                break;
            }
        }
        if(roomFound == null){
            throw new IllegalArgumentException("The room with the number " + roomNumber + " doesn't exist");
        }
        
        if(guestNumber <= 0 || guestNumber > roomFound.getMaximumCapacity()){
            throw new IllegalArgumentException("The number of guests is invalid or exceeds the maximun capacity of the room");
        }
        
        
        if(nights <= 0){
            throw new IllegalArgumentException("The number of nights must be above zero");
        }
        
        if(!roomFound.isEnabled()){
            throw new IllegalArgumentException("The room is out of service");
        }

        for(Reservation res : reservations){
            
            if(res.getRoom().getNumber() == roomNumber){
                
                if(res.getStatus() == -1 || res.getStatus() == 0){
                    throw new IllegalArgumentException("The room already has a reservation");
                }
            }
        }

        
        Reservation newReservation = new Reservation(id, guestFound, roomFound, guestNumber, nights);
        this.reservations.add(newReservation);
        
        return newReservation;
    }

    public Receipt findReceiptByNumber(int receiptNumber){
        Receipt receiptFound = null;
        for(Receipt newReceipt : this.receipts){
            if(newReceipt.getReceiptNumber() == receiptNumber){
                receiptFound = newReceipt;
                break;
            }
        }
        if(receiptFound == null){
            throw new IllegalArgumentException("The receipt with the number " + receiptNumber + " doesn't exist");
        }
        
        
        return receiptFound;
    }

    public List<Receipt> getReceipts(){
        return receipts;
    }
    public void registerReceipt(Receipt receiptToAdd) {
        if (receiptToAdd == null) {
            throw new IllegalArgumentException("The receipt cannot be null");
        }
        this.receipts.add(receiptToAdd);
    }

    public List<Guest> getGuests(){
        return guest;
    }
    public void registerGuest(Guest guestToAdd){
        if(guestToAdd == null){
            throw new IllegalArgumentException("The guest cannot be null");
        }
        this.guest.add(guestToAdd);    
    }

    public double calculateDoubleBedRateOneGuest_TCD1(){
        return getSingleBedRate() * 1.45;
    }
    public double calculateDoubleBedRateTwoGuest_TCDS2(){
        return getSingleBedRate() * 1.90;
    }
    public double calculateAdditionalPersonRate_TPA(){
        return getSingleBedRate() * 0.70;
    }
    public double getFloorSurchargePercentage(int floor){
        if (floor <= 3){
            return 0.0;
        } else if(floor <= 6){
            return 0.10;
        } else {
            return 0.20;
        }
    } 

    public Receipt confirmReservation(int reservationId, int receiptNumber) {
        Reservation resFound = null;
        for (Reservation res : this.reservations) {
            if (res.getId() == reservationId) {
                resFound = res;
                break;
            }
        }
        
        if (resFound == null) {
            throw new IllegalArgumentException("The reservation with ID " + reservationId + " doesn't exist");
        }
        
        // -1 = Pendiente, 0 = Confirmada, 1 = Cancelada
        if (resFound.getStatus() != -1) {
            throw new IllegalStateException("Only pending reservations can be confirmed");
        }
        
        // Validar que el número de recibo no esté repetido
        for (Receipt rec : this.receipts) {
            if (rec.getReceiptNumber() == receiptNumber) {
                throw new IllegalArgumentException("The receipt number " + receiptNumber + " is already in use");
            }
        }
        
        // Para calcular el total, la reserva le pedirá al hotel (this) las tarifas base
        double totalCost = resFound.calculateTotalCost(this); 
        
        // Crear el recibo tomando la "foto" inmutable de los datos
        // Nota: Ajusta los getters "getGuest()" o "getRoom()" si en tu clase Reservation los llamaste distinto
        Receipt newReceipt = new Receipt(
            receiptNumber, 
            resFound.getId(), 
            resFound.getGuest().getFullName(), 
            resFound.getRoom().getNumber(), 
            resFound.getNightsNumber(), 
            totalCost
        );
        
        resFound.setStatus(0); // Pasa a Confirmada
        this.receipts.add(newReceipt);
        
        return newReceipt;
    }

    public void cancelReservation(int reservationId) {
        Reservation resFound = null;
        for (Reservation res : this.reservations) {
            if (res.getId() == reservationId) {
                resFound = res;
                break;
            }
        }
        
        if (resFound == null) {
            throw new IllegalArgumentException("The reservation with ID " + reservationId + " doesn't exist");
        }
        
        // -1 = Pendiente, 0 = Confirmada, 1 = Cancelada
        if (resFound.getStatus() == 1) {
            throw new IllegalStateException("The reservation is already cancelled");
        }
        
        resFound.setStatus(1); // Pasa a Cancelada. Esto automáticamente libera la habitación.
    }
}

