const express = require('express');
const router = express.Router();
const Book = require('../models/Book');

// GET all books (also supports ?search= and ?category=)
router.get('/', async (req, res) => {
  try {
    const { search, category } = req.query;
    let filter = {};

    if (search) {
      filter.$or = [
        { title: { $regex: search, $options: 'i' } },
        { author: { $regex: search, $options: 'i' } },
        { isbn: { $regex: search, $options: 'i' } }
      ];
    }

    if (category) {
      filter.category = { $regex: category, $options: 'i' };
    }

    const books = await Book.find(filter).sort({ title: 1 });
    res.json(books);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

// GET single book
router.get('/:id', async (req, res) => {
  try {
    const book = await Book.findById(req.params.id);
    if (!book) return res.status(404).json({ error: 'Book not found' });
    res.json(book);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

// POST add a new book (Admin only)
router.post('/', async (req, res) => {
  try {
    const { title, author, category, isbn, totalCopies } = req.body;

    const book = new Book({
      title,
      author,
      category,
      isbn,
      totalCopies: totalCopies || 1,
      availableCopies: totalCopies || 1
    });

    await book.save();
    res.status(201).json(book);
  } catch (err) {
    res.status(400).json({ error: err.message });
  }
});

// PUT update a book (Admin only)
router.put('/:id', async (req, res) => {
  try {
    const updated = await Book.findByIdAndUpdate(req.params.id, req.body, {
      new: true,
      runValidators: true
    });
    if (!updated) return res.status(404).json({ error: 'Book not found' });
    res.json(updated);
  } catch (err) {
    res.status(400).json({ error: err.message });
  }
});

// DELETE a book (Admin only)
router.delete('/:id', async (req, res) => {
  try {
    const deleted = await Book.findByIdAndDelete(req.params.id);
    if (!deleted) return res.status(404).json({ error: 'Book not found' });
    res.json({ message: 'Book deleted successfully' });
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

module.exports = router;