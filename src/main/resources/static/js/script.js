document.addEventListener("DOMContentLoaded", function () {
    const entryForm = document.getElementById("entryForm");
    const transactionTable = document.getElementById("transactionTable");

    if (entryForm) {
        entryForm.addEventListener("submit", function (e) {
           
            
            const description = document.getElementById("description").value;
            const amount = document.getElementById("amount").value;
            const type = document.getElementById("type").value;
            
            const entry = { date, description, amount, type };
            let transactions = JSON.parse(localStorage.getItem("transactions")) || [];
            transactions.push(entry);
            localStorage.setItem("transactions", JSON.stringify(transactions));
            
            window.location.href = "home.html";
        });
    }

    if (transactionTable) {
        let transactions = JSON.parse(localStorage.getItem("transactions")) || [];
        transactionTable.innerHTML = "";
        transactions.forEach((entry, index) => {
            let row = transactionTable.insertRow();
            row.innerHTML = `
                <td>${entry.description}</td>
                <td class="${entry.type === 'earning' ? 'earning' : 'loss'}">${entry.amount}</td>
                <td><button class="delete-btn" data-index="${index}">Delete</button></td>
                <td><button class="alter-btn" data-index="${index}">Alter</button></td>
            `;
        });
    }
});
