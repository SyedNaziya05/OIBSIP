package Task1_OnlineReservationSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReservationManager {

    private Map<String, Reservation> reservations;
    private int pnrCounter;

    public ReservationManager() {
        reservations = new HashMap<>();
        pnrCounter = 10001;
    }

    public void makeReservation(Scanner scanner) {
        System.out.println("\n--- New Reservation ---");

        System.out.print("Enter Passenger Name: ");
        String passengerName = scanner.next();

        System.out.print("Enter Train Number: ");
        String trainNumber = scanner.next();

        System.out.print("Enter Train Name: ");
        String trainName = scanner.next();

        System.out.print("Enter Class Type (e.g. SL/3A/2A/1A): ");
        String classType = scanner.next();

        System.out.print("Enter Date of Journey (DD-MM-YYYY): ");
        String dateOfJourney = scanner.next();

        System.out.print("Enter From Place: ");
        String fromPlace = scanner.next();

        System.out.print("Enter To Place (Destination): ");
        String toPlace = scanner.next();

        String pnrNumber = "PNR" + pnrCounter++;

        Reservation reservation = new Reservation(pnrNumber, passengerName, trainNumber, trainName,
                classType, dateOfJourney, fromPlace, toPlace);

        reservations.put(pnrNumber, reservation);

        System.out.println("\nReservation successful! Your PNR Number is: " + pnrNumber);
        System.out.println("Please keep this PNR number safe for future reference.");
    }

    public void cancelReservation(Scanner scanner) {
        System.out.print("\nEnter PNR Number to cancel: ");
        String pnrNumber = scanner.next();

        Reservation reservation = reservations.get(pnrNumber);

        if (reservation == null) {
            System.out.println("No reservation found with this PNR number.");
            return;
        }

        System.out.println("\nReservation Details:");
        System.out.println(reservation);

        System.out.print("Do you want to confirm cancellation? (yes/no): ");
        String confirm = scanner.next().trim().toLowerCase();

        if (confirm.equals("yes") || confirm.equals("y")) {
            reservations.remove(pnrNumber);
            System.out.println("Reservation cancelled successfully.");
        } else {
            System.out.println("Cancellation aborted.");
        }
    }

    public void viewAllReservations() {
        System.out.println("\n--- All Reservations ---");

        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }

        for (Reservation reservation : reservations.values()) {
            System.out.println(reservation);
        }
    }
}