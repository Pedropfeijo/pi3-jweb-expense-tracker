document.getElementById('entryForm')?.addEventListener('submit', function(e) {
    e.preventDefault();
    const category = document.getElementById('category').value;
    const amount = document.getElementById('amount').value;
    const type = document.getElementById('type').value;
    
    const row = document.createElement('tr');
    row.className = type;
    row.innerHTML = `<td>${category}</td><td>$${amount}</td><td>${type.charAt(0).toUpperCase() + type.slice(1)}</td>`;
    document.getElementById('dataTable').appendChild(row);
    window.location.href = 'home.html';
});


