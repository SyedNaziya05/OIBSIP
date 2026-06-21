const API_BASE = '/api';

async function loadBooks() {
  const search = document.getElementById('searchInput').value.trim();
  const category = document.getElementById('categoryInput').value.trim();

  let url = `${API_BASE}/books?`;
  if (search) url += `search=${encodeURIComponent(search)}&`;
  if (category) url += `category=${encodeURIComponent(category)}&`;

  try {
    const res = await fetch(url);
    const books = await res.json();
    renderBooks(books);
  } catch (err) {
    console.error('Failed to load books:', err);
  }
}

function renderBooks(books) {
  const tbody = document.getElementById('booksTableBody');
  tbody.innerHTML = '';

  if (books.length === 0) {
    tbody.innerHTML = '<tr><td colspan="4">No books found.</td></tr>';
    return;
  }

  books.forEach((book) => {
    const row = document.createElement('tr');
    row.innerHTML = `
      <td>${book.title}</td>
      <td>${book.author}</td>
      <td><span class="tag">${book.category}</span></td>
      <td>${book.availableCopies} / ${book.totalCopies}</td>
    `;
    tbody.appendChild(row);
  });
}

async function loadHistory() {
  const memberId = document.getElementById('memberIdInput').value.trim();
  if (!memberId) {
    alert('Please enter your Member ID.');
    return;
  }

  try {
    const res = await fetch(`${API_BASE}/transactions/member/${memberId}`);
    const transactions = await res.json();
    renderHistory(transactions);
  } catch (err) {
    console.error('Failed to load history:', err);
    alert('Could not load history. Check the Member ID and try again.');
  }
}

function renderHistory(transactions) {
  const tbody = document.getElementById('historyTableBody');
  tbody.innerHTML = '';

  if (!transactions || transactions.length === 0) {
    tbody.innerHTML = '<tr><td colspan="6">No borrowing history found.</td></tr>';
    return;
  }

  transactions.forEach((t) => {
    const row = document.createElement('tr');
    row.innerHTML = `
      <td>${t.book ? t.book.title : 'N/A'}</td>
      <td>${new Date(t.issueDate).toLocaleDateString()}</td>
      <td>${new Date(t.dueDate).toLocaleDateString()}</td>
      <td>${t.returnDate ? new Date(t.returnDate).toLocaleDateString() : '-'}</td>
      <td class="${t.fineAmount > 0 ? 'fine' : ''}">${t.fineAmount > 0 ? '₹' + t.fineAmount : '-'}</td>
      <td><span class="tag">${t.status}</span></td>
    `;
    tbody.appendChild(row);
  });
}

// Load all books on page load
window.addEventListener('DOMContentLoaded', loadBooks);