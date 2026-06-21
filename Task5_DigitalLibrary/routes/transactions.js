const express = require('express');
const router = express.Router();
const Transaction = require('../models/Transaction');
const Book = require('../models/Book');
const Member = require('../models/Member');

const LOAN_PERIOD_DAYS = 14;
const FINE_PER_DAY = 5; // currency units per day overdue

// GET all transactions (with book & member details populated)
router.get('/', async (req, res) => {
  try {
    const transactions = await Transaction.find()
      .populate('book', 'title author isbn')
      .populate('member', 'name email')
      .sort({ issueDate: -1 });
    res.json(transactions);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

// POST issue a book to a member
router.post('/issue', async (req, res) => {
  try {
    const { bookId, memberId } = req.body;

    const book = await Book.findById(bookId);
    if (!book) return res.status(404).json({ error: 'Book not found' });

    const member = await Member.findById(memberId);
    if (!member) return res.status(404).json({ error: 'Member not found' });

    if (book.availableCopies <= 0) {
      return res.status(400).json({ error: 'No available copies for this book' });
    }

    const issueDate = new Date();
    const dueDate = new Date();
    dueDate.setDate(dueDate.getDate() + LOAN_PERIOD_DAYS);

    const transaction = new Transaction({
      book: book._id,
      member: member._id,
      issueDate,
      dueDate
    });

    book.availableCopies -= 1;
    await book.save();
    await transaction.save();

    res.status(201).json(transaction);
  } catch (err) {
    res.status(400).json({ error: err.message });
  }
});

// PUT return a book and calculate fine if overdue
router.put('/return/:transactionId', async (req, res) => {
  try {
    const transaction = await Transaction.findById(req.params.transactionId);
    if (!transaction) return res.status(404).json({ error: 'Transaction not found' });

    if (transaction.status === 'returned') {
      return res.status(400).json({ error: 'This book has already been returned' });
    }

    const returnDate = new Date();
    let fineAmount = 0;

    if (returnDate > transaction.dueDate) {
      const overdueDays = Math.ceil(
        (returnDate - transaction.dueDate) / (1000 * 60 * 60 * 24)
      );
      fineAmount = overdueDays * FINE_PER_DAY;
    }

    transaction.returnDate = returnDate;
    transaction.fineAmount = fineAmount;
    transaction.status = 'returned';
    await transaction.save();

    const book = await Book.findById(transaction.book);
    if (book) {
      book.availableCopies += 1;
      await book.save();
    }

    res.json({
      message: 'Book returned successfully',
      fineAmount,
      transaction
    });
  } catch (err) {
    res.status(400).json({ error: err.message });
  }
});

// GET transactions for a specific member (borrowing history)
router.get('/member/:memberId', async (req, res) => {
  try {
    const transactions = await Transaction.find({ member: req.params.memberId })
      .populate('book', 'title author isbn')
      .sort({ issueDate: -1 });
    res.json(transactions);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

module.exports = router;