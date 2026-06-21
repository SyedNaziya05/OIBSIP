const API_BASE = '/api';
let isAdminLoggedIn = false;

async function adminLogin() {
  const username = document.getElementById('adminUsername').value.trim();
  const password = document.getElementById('adminPassword').value.trim();
  const msg = document.getElementById('loginMsg');

  try {
    const res = await fetch(`${API_BASE}/auth/admin-login`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username, password })
    });
    const data = await res.json();

    if (data.success) {
      isAdminLoggedIn = true;
      document.getElementById('loginSection').style.display = 'none';
      document.getElementById('dashboardSection').style.display = 'block';
      loadAdminBooks();
      loadAdminMembers();
      loadAdminTransactions();
    } else {
      msg.textContent = data.message;
      msg.className = 'error-msg';
    }
  } catch (err) {
    msg.textContent = 'Login failed. Check server connection.';
    msg.className = 'error-msg';
  }
}

// ---------- BOOKS ----------

async function addBook() {
  const title = document.getElementById('bookTitle').value.trim();
  const author = document.getElementById('bookAuthor').value.trim();
  const category = document.getElementById('bookCategory').value.trim();
  const isbn = document.getElementById('bookIsbn').value.trim();
  const totalCopies = parseInt(document.getElementById('bookCopies').value) || 1;
  const msg = document.getElementById('bookMsg');

  try {
    const res = await fetch(`${API_BASE}/books`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ title, author, category, isbn, totalCopies })
    });
    const data = await res.json();

    if (res.ok) {
      msg.textContent = 'Book added successfully!';
      msg.className = 'success-msg';
      document.getElementById('bookTitle').value = '';
      document.getElementById('bookAuthor').value = '';
      document.getElementById('bookCategory').value = '';
      document.getElementById('bookIsbn').value = '';
      document.getElementById('bookCopies').value = '1';
      loadAdminBooks();
    } else {
      msg.textContent = data.error || 'Failed to add book.';
      msg.className = 'error-msg';
    }
  } catch (err) {
    msg.textContent = 'Error adding book.';
    msg.className = 'error-msg';
  }
}

async function loadAdminBooks() {
  const res = await fetch(`${API_BASE}/books`);
  const books = await res.json();
  const tbody = document.getElementById('adminBooksTableBody');
  tbody.innerHTML = '';

  books.forEach((book) => {
    const row = document.createElement('tr');
    row.innerHTML = `
      <td>${book.title}<br><small style="color:#64748b;">ID: ${book._id}</small></td>
      <td>${book.author}</td>
      <td>${book.category}</td>
      <td>${book.availableCopies}/${book.totalCopies}</td>
      <td>
        <button class="btn btn-danger" onclick="deleteBook('${book._id}')">Delete</button>
      </td>
    `;
    tbody.appendChild(row);
  });
}

async function deleteBook(id) {
  if (!confirm('Are you sure you want to delete this book?')) return;
  await fetch(`${API_BASE}/books/${id}`, { method: 'DELETE' });
  loadAdminBooks();
}

// ---------- MEMBERS ----------

async function addMember() {
  const name = document.getElementById('memberName').value.trim();
  const email = document.getElementById('memberEmail').value.trim();
  const phone = document.getElementById('memberPhone').value.trim();
  const msg = document.getElementById('memberMsg');

  try {
    const res = await fetch(`${API_BASE}/members`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ name, email, phone })
    });
    const data = await res.json();

    if (res.ok) {
      msg.textContent = 'Member added successfully!';
      msg.className = 'success-msg';
      document.getElementById('memberName').value = '';
      document.getElementById('memberEmail').value = '';
      document.getElementById('memberPhone').value = '';
      loadAdminMembers();
    } else {
      msg.textContent = data.error || 'Failed to add member.';
      msg.className = 'error-msg';
    }
  } catch (err) {
    msg.textContent = 'Error adding member.';
    msg.className = 'error-msg';
  }
}

async function loadAdminMembers() {
  const res = await fetch(`${API_BASE}/members`);
  const members = await res.json();
  const tbody = document.getElementById('adminMembersTableBody');
  tbody.innerHTML = '';

  members.forEach((member) => {
    const row = document.createElement('tr');
    row.innerHTML = `
      <td><small>${member._id}</small></td>
      <td>${member.name}</td>
      <td>${member.email}</td>
      <td>
        <button class="btn btn-danger" onclick="deleteMember('${member._id}')">Delete</button>
      </td>
    `;
    tbody.appendChild(row);
  });
}

async function deleteMember(id) {
  if (!confirm('Are you sure you want to delete this member?')) return;
  await fetch(`${API_BASE}/members/${id}`, { method: 'DELETE' });
  loadAdminMembers();
}

// ---------- TRANSACTIONS (ISSUE / RETURN) ----------

async function issueBook() {
  const bookId = document.getElementById('issueBookId').value.trim();
  const memberId = document.getElementById('issueMemberId').value.trim();
  const msg = document.getElementById('issueMsg');

  try {
    const res = await fetch(`${API_BASE}/transactions/issue`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ bookId, memberId })
    });
    const data = await res.json();

    if (res.ok) {
      msg.textContent = 'Book issued successfully!';
      msg.className = 'success-msg';
      document.getElementById('issueBookId').value = '';
      document.getElementById('issueMemberId').value = '';
      loadAdminBooks();
      loadAdminTransactions();
    } else {
      msg.textContent = data.error || 'Failed to issue book.';
      msg.className = 'error-msg';
    }
  } catch (err) {
    msg.textContent = 'Error issuing book.';
    msg.className = 'error-msg';
  }
}

async function loadAdminTransactions() {
  const res = await fetch(`${API_BASE}/transactions`);
  const transactions = await res.json();
  const tbody = document.getElementById('adminTransactionsTableBody');
  tbody.innerHTML = '';

  transactions.forEach((t) => {
    const row = document.createElement('tr');
    row.innerHTML = `
      <td>${t.book ? t.book.title : 'N/A'}</td>
      <td>${t.member ? t.member.name : 'N/A'}</td>
      <td>${new Date(t.issueDate).toLocaleDateString()}</td>
      <td>${new Date(t.dueDate).toLocaleDateString()}</td>
      <td class="${t.fineAmount > 0 ? 'fine' : ''}">${t.fineAmount > 0 ? '₹' + t.fineAmount : '-'}</td>
      <td><span class="tag">${t.status}</span></td>
      <td>
        ${t.status === 'issued'
          ? `<button class="btn" onclick="returnBook('${t._id}')">Return</button>`
          : '-'}
      </td>
    `;
    tbody.appendChild(row);
  });
}

async function returnBook(transactionId) {
  try {
    const res = await fetch(`${API_BASE}/transactions/return/${transactionId}`, {
      method: 'PUT'
    });
    const data = await res.json();

    if (res.ok) {
      alert(data.fineAmount > 0
        ? `Book returned. Fine charged: ₹${data.fineAmount}`
        : 'Book returned successfully. No fine.');
      loadAdminBooks();
      loadAdminTransactions();
    } else {
      alert(data.error || 'Failed to return book.');
    }
  } catch (err) {
    alert('Error returning book.');
  }
}