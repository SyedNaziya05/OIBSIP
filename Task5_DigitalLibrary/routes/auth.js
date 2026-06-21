const express = require('express');
const router = express.Router();
require('dotenv').config();

// POST admin login
router.post('/admin-login', (req, res) => {
  const { username, password } = req.body;

  const validUsername = process.env.ADMIN_USERNAME || 'admin';
  const validPassword = process.env.ADMIN_PASSWORD || 'admin123';

  if (username === validUsername && password === validPassword) {
    res.json({ success: true, message: 'Admin login successful' });
  } else {
    res.status(401).json({ success: false, message: 'Invalid admin credentials' });
  }
});

module.exports = router;