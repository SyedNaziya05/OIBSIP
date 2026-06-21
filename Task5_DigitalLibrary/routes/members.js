const express = require('express');
const router = express.Router();
const Member = require('../models/Member');

// GET all members
router.get('/', async (req, res) => {
  try {
    const members = await Member.find().sort({ name: 1 });
    res.json(members);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

// POST add a new member (Admin only)
router.post('/', async (req, res) => {
  try {
    const { name, email, phone } = req.body;
    const member = new Member({ name, email, phone });
    await member.save();
    res.status(201).json(member);
  } catch (err) {
    res.status(400).json({ error: err.message });
  }
});

// PUT update a member (Admin only)
router.put('/:id', async (req, res) => {
  try {
    const updated = await Member.findByIdAndUpdate(req.params.id, req.body, {
      new: true,
      runValidators: true
    });
    if (!updated) return res.status(404).json({ error: 'Member not found' });
    res.json(updated);
  } catch (err) {
    res.status(400).json({ error: err.message });
  }
});

// DELETE a member (Admin only)
router.delete('/:id', async (req, res) => {
  try {
    const deleted = await Member.findByIdAndDelete(req.params.id);
    if (!deleted) return res.status(404).json({ error: 'Member not found' });
    res.json({ message: 'Member deleted successfully' });
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
});

module.exports = router;