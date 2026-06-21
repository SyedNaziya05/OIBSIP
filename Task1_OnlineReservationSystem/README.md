# Task 1: Online Reservation System

## Description
A console-based Java application that simulates an online train reservation system. Users log in with a valid Login ID and password before accessing the system. Once logged in, they can make a new reservation, cancel an existing reservation using their PNR number, or view all current reservations.

## Features
- Login form with ID and password authentication (limited to 3 attempts)
- Make a reservation by entering passenger and journey details
- Auto-generated unique PNR number for each reservation
- Cancel a reservation by PNR number, with a confirmation prompt before cancelling
- View all current reservations
- Input handled via console (Scanner)

## Classes
- **NaziyaSyed_Task1.java** – Main class; handles login and menu navigation.
- **UserManager.java** – Manages user login credentials and authentication.
- **Reservation.java** – Data model representing a single reservation.
- **ReservationManager.java** – Handles creating, cancelling, and viewing reservations.

## Demo Login
| Login ID | Password |
|----------|----------|
| naziya   | pass123  |

## How to Run
1. Make sure you have Java (JDK 8+) installed.
2. Open a terminal in this folder.
3. Compile all files:
   ```
   javac *.java
   ```
4. Run the program:
   ```
   java NaziyaSyed_Task1
   ```
5. Log in using the demo credentials above and choose an option from the menu.

## Tech Stack
- Java (Console application)

## Author
Syed Naziya — Oasis Infobyte Internship (OIBSIP)