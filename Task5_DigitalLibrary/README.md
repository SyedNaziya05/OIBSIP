# Task 5: Digital Library Management System

## Description
A full-stack web application that automates library operations end-to-end — book cataloging, member management, issuing/returning books, and fine generation. Built with a Node.js/Express backend, a MongoDB database, and a vanilla HTML/CSS/JS frontend. The system has two modules: an **Admin module** with full control over the system, and a **User module** with limited, view-based access.

## Features

### Admin Module (full control)
- Add, update, and delete books
- Add, update, and delete members
- Issue a book to a member (checks copy availability, sets a 14-day due date)
- Return a book (auto-calculates a fine of ₹5/day if returned late)
- View all books, members, and transactions

### User Module (limited access)
- Browse the full book catalog
- Search books by title, author, or ISBN
- Filter books by category
- View personal borrowing history using a Member ID
- Email option to contact the library for queries

## Tech Stack
- **Backend:** Node.js, Express.js
- **Database:** MongoDB (Mongoose ODM)
- **Frontend:** HTML, CSS, vanilla JavaScript (Fetch API)

## Project Structure
```
Task5_DigitalLibrary/
├── NaziyaSyed_Task5.js     # Main server entry point
├── package.json
├── .env                    # Environment config (not committed; see .env.example)
├── models/                 # Mongoose schemas: Book, Member, Transaction
├── routes/                 # Express routes: books, members, transactions, auth
└── public/                 # Frontend: index.html, user.html, admin.html, JS, CSS
```

## How to Run
1. Make sure Node.js and MongoDB (local or Atlas) are available.
2. Open a terminal in the `Task5_DigitalLibrary` folder.
3. Install dependencies:
   ```
   npm install
   ```
4. Create a `.env` file (use `.env.example` as a reference) and set your MongoDB connection string:
   ```
   PORT=5000
   MONGO_URI=your-mongodb-connection-string
   ADMIN_USERNAME=admin
   ADMIN_PASSWORD=admin123
   ```
5. Start the server:
   ```
   npm start
   ```
6. Open `http://localhost:5000` in your browser.

## Demo Admin Login
| Username | Password  |
|----------|-----------|
| admin    | admin123  |

## Fine Policy
- Loan period: 14 days from issue date
- Fine: ₹5 per day overdue, calculated automatically on return

## Author
Syed Naziya — Oasis Infobyte Internship (OIBSIP)