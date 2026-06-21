# Task 3: ATM Interface

## Description
A console-based Java ATM application that simulates basic banking operations. The system uses multiple classes to handle authentication, account management, and ATM operations. Users log in with a User ID and PIN, after which they can view transaction history, withdraw, deposit, or transfer funds to another account.

## Features
- User authentication using User ID and PIN
- View transaction history
- Withdraw funds (with insufficient balance check)
- Deposit funds
- Transfer funds to another account
- Input validation for invalid amounts and choices

## Classes
- **NaziyaSyed_Task3.java** – Main class; handles login and menu navigation.
- **Account.java** – Represents a bank account (balance, PIN, transaction history).
- **BankSystem.java** – Manages all accounts and handles authentication.
- **ATMOperations.java** – Handles withdraw, deposit, transfer, and transaction history operations.

## Demo Accounts
| User ID | PIN  | Starting Balance |
|---------|------|-------------------|
| 1001    | 1234 | 5000              |
| 1002    | 5678 | 10000             |

## How to Run
1. Make sure you have Java (JDK 8+) installed.
2. Open a terminal in this folder.
3. Compile all files:
   ```
   javac *.java
   ```
4. Run the program:
   ```
   java NaziyaSyed_Task3
   ```
5. Log in using one of the demo accounts above and choose an operation from the menu.

## Tech Stack
- Java (Console application)

## Author
Syed Naziya — Oasis Infobyte Internship (OIBSIP)