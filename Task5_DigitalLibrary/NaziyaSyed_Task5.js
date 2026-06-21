const dns = require("dns");
dns.setServers(["1.1.1.1", "8.8.8.8"]);
require('dotenv').config();
const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');
const path = require('path');

const bookRoutes = require('./routes/books');
const memberRoutes = require('./routes/members');
const transactionRoutes = require('./routes/transactions');
const authRoutes = require('./routes/auth');

const app = express();
const PORT = process.env.PORT || 5000;
const MONGO_URI = process.env.MONGO_URI || 'mongodb://127.0.0.1:27017/digital_library';

app.use(cors());
app.use(express.json());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/api/books', bookRoutes);
app.use('/api/members', memberRoutes);
app.use('/api/transactions', transactionRoutes);
app.use('/api/auth', authRoutes);

mongoose.connect(MONGO_URI)
  .then(() => console.log('MongoDB connected successfully'))
  .catch((err) => console.error('MongoDB connection error:', err.message));

app.listen(PORT, () => {
  console.log(`Digital Library Management System running on http://localhost:${PORT}`);
});