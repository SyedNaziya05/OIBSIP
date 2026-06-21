package Task1_OnlineReservationSystem;

public class Reservation {

    private String pnrNumber;
    private String passengerName;
    private String trainNumber;
    private String trainName;
    private String classType;
    private String dateOfJourney;
    private String fromPlace;
    private String toPlace;

    public Reservation(String pnrNumber, String passengerName, String trainNumber, String trainName,
                        String classType, String dateOfJourney, String fromPlace, String toPlace) {
        this.pnrNumber = pnrNumber;
        this.passengerName = passengerName;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.dateOfJourney = dateOfJourney;
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
    }

    public String getPnrNumber() {
        return pnrNumber;
    }

    @Override
    public String toString() {
        return "PNR: " + pnrNumber +
                " | Passenger: " + passengerName +
                " | Train: " + trainNumber + " (" + trainName + ")" +
                " | Class: " + classType +
                " | Date: " + dateOfJourney +
                " | From: " + fromPlace +
                " | To: " + toPlace;
    }
}