package view;

import model.Hotel;
import model.SingleRoom;
import model.DoubleRoom;
import model.FamilyRoom;
import model.Suite;
import model.Guest;
import model.Reservation;
import model.Receipt;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== HOTEL MANAGEMENT SYSTEM ===");
        
        Hotel myHotel = new Hotel(
            1, 
            "Ocean View Resort", 
            "Avenida Principal 123", 
            8,        // totalFloors (minimum 8)
            50000.0,  // TBH (Base Rate)
            40000.0,  // TCS (Single Bed Rate)
            30000.0,  // TMI (Minibar Rate)
            30.0,     // PVM (Sea View Percentage)
            3500.0    // RCP (Premium TV Surcharge)
        );
        
        System.out.println("Hotel initialized: " + myHotel.getName() + " (" + myHotel.getFloors() + " floors)");
        
        initializeRooms(myHotel);
        System.out.println("Total registered rooms: " + myHotel.getRooms().size());

        // 3. Guest Registration
        Guest guest1 = new Guest(1001, "Alejandro Ocampo", "CC12345678", "3001234567", "alejandro@email.com");
        Guest guest2 = new Guest(1002, "Joshua Franco", "CC87654321", "3109876543", "joshua@email.com");
        myHotel.registerGuest(guest1);
        myHotel.registerGuest(guest2);
        System.out.println("Guests registered successfully.");

        System.out.println("\n--- RESERVATION AND COST TESTS ---");
        
        try {
            System.out.println("> Attempting reservation for Suite 801 (1 guest, 3 nights)...");
            Reservation res1 = myHotel.createReservation(5001, 1001, 801, 1, 3);
            System.out.println("Projected total cost: $" + res1.calculateTotalCost(myHotel));
            
            // Reservation confirmation
            Receipt receipt1 = myHotel.confirmReservation(5001, 9001);
            System.out.println("Reservation confirmed. Receipt generated: #" + receipt1.getReceiptNumber());
            System.out.println("Total amount charged on receipt: $" + receipt1.getTotalCost());
            
        } catch (Exception e) {
            System.out.println("Error in Test 1: " + e.getMessage());
        }

        try {
            System.out.println("\n> Attempting reservation for Family Room 703 (8 guests, 2 nights)...");
            Reservation res2 = myHotel.createReservation(5002, 1002, 703, 8, 2);
            System.out.println("Projected total cost: $" + res2.calculateTotalCost(myHotel));
            
        } catch (Exception e) {
            System.out.println("Error in Test 2: " + e.getMessage());
        }

        try {
            System.out.println("\n> Attempting invalid reservation for Single Room 101 (3 guests, capacity exceeded)...");
            myHotel.createReservation(5003, 1001, 101, 3, 1);
            System.out.println("Validation failed to catch the error.");
        } catch (Exception e) {
            System.out.println("Exception successfully caught: " + e.getMessage());
        }

        try {
            System.out.println("\n> Cancelling reservation 5002 (Family Room 703)...");
            myHotel.cancelReservation(5002);
            System.out.println("Reservation cancelled successfully.");
        } catch (Exception e) {
            System.out.println("Error in Test 4: " + e.getMessage());
        }
    
        try {
            System.out.println("\n> Searching for receipt #9001...");
            Receipt foundReceipt = myHotel.findReceiptByNumber(9001);
            System.out.println("Receipt found.");
            System.out.println("Guest: " + foundReceipt.getResponsibleGuestName());
            System.out.println("Room: " + foundReceipt.getRoomNumber());
            System.out.println("Nights: " + foundReceipt.getNightsCount());
            System.out.println("Total Paid: $" + foundReceipt.getTotalCost());
        } catch (Exception e) {
            System.out.println("Error in Test 5: " + e.getMessage());
        }
    }

    private static void initializeRooms(Hotel hotel) {
        
        // FLOOR 8: 4 Suites
        hotel.registerRoom(new Suite(801, 8, true, hotel));  
        hotel.registerRoom(new Suite(802, 8, true, hotel));  
        hotel.registerRoom(new Suite(803, 8, false, hotel)); 
        hotel.registerRoom(new Suite(804, 8, false, hotel)); 

        // FLOOR 7: 2 Suites front, 2 Family Rooms back
        hotel.registerRoom(new Suite(701, 7, true, hotel));
        hotel.registerRoom(new Suite(702, 7, true, hotel));
        hotel.registerRoom(new FamilyRoom(703, 7, 4, false, true, hotel)); 
        hotel.registerRoom(new FamilyRoom(704, 7, 3, false, true, hotel)); 

        // FLOOR 6: 2 Family Rooms front, 2 Double Rooms back
        hotel.registerRoom(new FamilyRoom(601, 6, 2, true, true, hotel));  // 2 Single beds (Cap 6), Premium TV
        hotel.registerRoom(new FamilyRoom(602, 6, 2, true, false, hotel)); // 2 Single beds (Cap 6), No Premium TV
        hotel.registerRoom(new DoubleRoom(603, 6, false, true, hotel));    // Premium TV
        hotel.registerRoom(new DoubleRoom(604, 6, false, false, hotel));

        // FLOOR 5: 4 Double Rooms
        hotel.registerRoom(new DoubleRoom(501, 5, true, true, hotel));
        hotel.registerRoom(new DoubleRoom(502, 5, true, false, hotel));
        hotel.registerRoom(new DoubleRoom(503, 5, false, true, hotel));
        hotel.registerRoom(new DoubleRoom(504, 5, false, false, hotel));

        // FLOOR 4: 2 Double Rooms front, 2 Single Rooms back
        hotel.registerRoom(new DoubleRoom(401, 4, true, false, hotel));
        hotel.registerRoom(new DoubleRoom(402, 4, true, false, hotel));
        hotel.registerRoom(new SingleRoom(403, 4, false, true, hotel));
        hotel.registerRoom(new SingleRoom(404, 4, false, false, hotel));

        // FLOOR 3: 4 Single Rooms
        hotel.registerRoom(new SingleRoom(301, 3, true, false, hotel));
        hotel.registerRoom(new SingleRoom(302, 3, true, false, hotel));
        hotel.registerRoom(new SingleRoom(303, 3, false, false, hotel));
        hotel.registerRoom(new SingleRoom(304, 3, false, false, hotel));

        // FLOOR 2: 4 Single Rooms
        hotel.registerRoom(new SingleRoom(201, 2, true, false, hotel));
        hotel.registerRoom(new SingleRoom(202, 2, true, false, hotel));
        hotel.registerRoom(new SingleRoom(203, 2, false, false, hotel));
        hotel.registerRoom(new SingleRoom(204, 2, false, false, hotel));

        // FLOOR 1: 4 Single Rooms
        hotel.registerRoom(new SingleRoom(101, 1, true, false, hotel));
        hotel.registerRoom(new SingleRoom(102, 1, true, false, hotel));
        hotel.registerRoom(new SingleRoom(103, 1, false, false, hotel));
        hotel.registerRoom(new SingleRoom(104, 1, false, false, hotel));
    }
}


