import java.util.*;
import java.io.*;

class Room {

    int roomNumber;
    String roomType;
    int price;
    boolean available;

    Room(int roomNumber, String roomType, int price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.available = true;
    }

    void displayRoom() {
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Room Type: " + roomType);
        System.out.println("Price: Rs. " + price);
        System.out.println("Available: " + available);
    }
}

public class Hotelreservationsystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Room standardRoom = new Room(101, "Standard", 1500);
        Room deluxeRoom = new Room(201, "Deluxe", 2500);
        Room suiteRoom = new Room(301, "Suite", 4000);

        System.out.println("--- HOTEL RESERVATION SYSTEM ---");

        System.out.println("Enter your Name:");
        String Name = sc.next();

        System.out.println("Welcome " + Name + "!");

        
        System.out.println("\n--- Search Room ---");
        System.out.println("Enter room number to search:");
        int searchRoom = sc.nextInt();

        if (searchRoom == 101) {
            standardRoom.displayRoom();
        }
        else if (searchRoom == 201) {
            deluxeRoom.displayRoom();
        }
        else if (searchRoom == 301) {
            suiteRoom.displayRoom();
        }
        else {
            System.out.println("Room Not Found!");
        }

        
        System.out.println("\n--- Available Room Categories ---");
        System.out.println("1. Standard");
        System.out.println("2. Deluxe");
        System.out.println("3. Suite");

        System.out.println("Choose Room Category:");
        int choice = sc.nextInt();

        int price = 0;
        String RoomType = "";
        int RoomNumber = 0;

        Room selectedRoom = null;

        if (choice == 1) {
            selectedRoom = standardRoom;
        }
        else if (choice == 2) {
            selectedRoom = deluxeRoom;
        }
        else if (choice == 3) {
            selectedRoom = suiteRoom;
        }
        else {
            System.out.println("Invalid Choice");
        }

        
        if (selectedRoom != null) {

            if (selectedRoom.available) {

                RoomType = selectedRoom.roomType;
                RoomNumber = selectedRoom.roomNumber;
                price = selectedRoom.price;

                System.out.println("Room Type: " + RoomType);
                System.out.println("Room Number: " + RoomNumber);
                System.out.println("Price per night: " + price);

                System.out.println("Enter the number of nights:");
                int nights = sc.nextInt();

                int totalbills = price * nights;

                System.out.println("\n--- Booking Details ---");
                System.out.println("Customer Name: " + Name);
                System.out.println("Room Type: " + RoomType);
                System.out.println("Room Number: " + RoomNumber);
                System.out.println("Number of Nights: " + nights);
                System.out.println("Room Price per night: " + price);
                System.out.println("Total Bills: " + totalbills);

                
                System.out.println("\n--- Payment Method ---");
                System.out.println("1. CASH");
                System.out.println("2. UPI");
                System.out.println("3. CARD");

                System.out.println("Choose payment method:");
                int paymentChoice = sc.nextInt();

                String paymentMethod = "";

                if (paymentChoice == 1) {
                    paymentMethod = "CASH";
                }
                else if (paymentChoice == 2) {
                    paymentMethod = "UPI";
                }
                else if (paymentChoice == 3) {
                    paymentMethod = "CARD";
                }
                else {
                    System.out.println("Invalid Payment Method");
                }

                if (paymentChoice >= 1 && paymentChoice <= 3) {

                    System.out.println("Payment Method: " + paymentMethod);
                    System.out.println("Payment Status: Successful");

                    
                    selectedRoom.available = false;

                    System.out.println("Booking Confirmed!");

                    
                    try {

                        FileWriter fw = new FileWriter("bookings.txt", true);

                        fw.write("Customer Name: " + Name + "\n");
                        fw.write("Room Number: " + RoomNumber + "\n");
                        fw.write("Room Type: " + RoomType + "\n");
                        fw.write("Number of Nights: " + nights + "\n");
                        fw.write("Price per Night: " + price + "\n");
                        fw.write("Total Bill: " + totalbills + "\n");
                        fw.write("Payment Method: " + paymentMethod + "\n");
                        fw.write("Booking Status: Confirmed\n");
                        fw.write("-----------------------------\n");

                        fw.close();

                        System.out.println("Booking saved to file successfully!");

                    }
                    catch (IOException e) {
                        System.out.println("Error while saving booking.");
                    }

                }
            }
            else {
                System.out.println("Sorry! This room is already booked.");
            }
        }

        
        System.out.println("\n--- Reservation Management ---");
        System.out.println("1. Keep Booking");
        System.out.println("2. Cancel Booking");

        System.out.println("Enter your choice:");
        int ReservationChoice = sc.nextInt();

        if (ReservationChoice == 1) {

            System.out.println("Booking Kept Successfully!");

        }
        else if (ReservationChoice == 2) {

            if (selectedRoom != null) {

                selectedRoom.available = true;

                System.out.println("Booking Cancelled Successfully!");
                System.out.println("Room " + selectedRoom.roomNumber + " is now available.");

                try {

                    FileWriter fw = new FileWriter("bookings.txt", true);

                    fw.write("Customer Name: " + Name + "\n");
                    fw.write("Room Number: " + selectedRoom.roomNumber + "\n");
                    fw.write("Booking Status: Cancelled\n");
                    fw.write("-----------------------------\n");

                    fw.close();

                }
                catch (IOException e) {
                    System.out.println("Error while updating file.");
                }
            }
        }
        else {

            System.out.println("Invalid Option!");
        }

      
        System.out.println("\n--- Final Room Status ---");

        standardRoom.displayRoom();
        System.out.println();

        deluxeRoom.displayRoom();
        System.out.println();

        suiteRoom.displayRoom();

        sc.close();
    }
}