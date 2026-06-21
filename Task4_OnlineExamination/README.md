# Task 4: Online Examination System

## Description
A console-based Java application that simulates an online MCQ-based examination. Users log in, can update their profile/password, and take a timed exam consisting of multiple-choice questions. The exam automatically submits when the time limit expires, even if the user hasn't answered all questions, and the session can be closed via logout.

## Features
- Login with username and password
- Update Profile and Password
- MCQ-based exam with options A, B, C, D
- 60-second timer for the entire exam with true non-blocking auto-submit
- Score calculation and Pass/Fail result based on percentage
- Closing session and Logout

## Classes
- **NaziyaSyed_Task4.java** – Main class; handles login and menu navigation.
- **UserAccountManager.java** – Manages login credentials and profile/password updates.
- **Question.java** – Data model representing a single MCQ with options and correct answer.
- **ExamEngine.java** – Runs the timed exam, scores the result, and auto-submits when time expires.
- **InputReader.java** – Utility class that reads all console input on a single dedicated background thread, enabling safe, non-blocking timed reads (used for the exam timer) without corrupting the input stream.

## Demo Login
| Username | Password |
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
   java NaziyaSyed_Task4
   ```
5. Log in using the demo credentials, then choose "Start Exam" from the menu.

## Notes
- All console input flows through `InputReader`, a single background thread reading lines into a queue. The exam timer polls this queue with a timeout, so the exam auto-submits exactly when time runs out without needing extra input, and without the input stream getting corrupted (a common issue when interrupting threads blocked on `Scanner`).

## Tech Stack
- Java (Console application)

## Author
Syed Naziya — Oasis Infobyte Internship (OIBSIP)