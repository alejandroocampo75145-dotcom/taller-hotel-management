package model;

public class Receipt {
    private int receiptNumber;
    private int reservationId;
    private String responsibleGuestName;
    private int roomNumber;
    private int nightsCount;
    private double totalCost;

    public Receipt(int receiptNumber, int reservationId, String responsibleGuestName, int roomNumber, int nightsCount, double totalCost) {
        setReceiptNumber(receiptNumber);
        setReservationId(reservationId);
        setResponsibleGuestName(responsibleGuestName);
        setRoomNumber(roomNumber);
        setNightsCount(nightsCount);
        setTotalCost(totalCost);
    }

    public int getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(int receiptNumber) {
        if (receiptNumber <= 0) {
            throw new IllegalArgumentException("The receipt number must be positive");
        }
        this.receiptNumber = receiptNumber;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getResponsibleGuestName() {
        return responsibleGuestName;
    }

    public void setResponsibleGuestName(String responsibleGuestName) {
        if (responsibleGuestName == null || responsibleGuestName.isBlank()) {
            throw new IllegalArgumentException("The guest name cannot be empty");
        }
        this.responsibleGuestName = responsibleGuestName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getNightsCount() {
        return nightsCount;
    }

    public void setNightsCount(int nightsCount) {
        if (nightsCount <= 0) {
            throw new IllegalArgumentException("The number of nights must be greater than zero");
        }
        this.nightsCount = nightsCount;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        if (totalCost < 0) {
            throw new IllegalArgumentException("The total cost cannot be negative");
        }
        this.totalCost = totalCost;
    }
}

