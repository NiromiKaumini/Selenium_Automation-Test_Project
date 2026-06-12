const books = [
    {
        id: "B001",
        title: "Clean Code",
        author: "Robert C. Martin",
        pages: 464,
        status: "AVAILABLE",
        borrowedBy: ""
    },
    {
        id: "B002",
        title: "Effective Java",
        author: "Joshua Bloch",
        pages: 416,
        status: "AVAILABLE",
        borrowedBy: ""
    }
];

const history = [];

const bookTableBody = document.getElementById("bookTableBody");
const historyTableBody = document.getElementById("historyTableBody");
const message = document.getElementById("message");

function showMessage(text, type) {
    message.textContent = text;
    message.className = "message " + type;
}

function renderBooks(bookList = books) {
    bookTableBody.innerHTML = "";

    bookList.forEach(book => {
        const row = document.createElement("tr");
        row.setAttribute("data-book-id", book.id);

        row.innerHTML = `
            <td>${book.id}</td>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.pages}</td>
            <td>${book.status}</td>
            <td>${book.borrowedBy}</td>
        `;

        bookTableBody.appendChild(row);
    });
}

function renderHistory() {
    historyTableBody.innerHTML = "";

    history.forEach(record => {
        const row = document.createElement("tr");

        row.innerHTML = `
            <td>${record.bookId}</td>
            <td>${record.member}</td>
            <td>${record.action}</td>
        `;

        historyTableBody.appendChild(row);
    });
}

function findBook(bookId) {
    return books.find(book => book.id.toLowerCase() === bookId.toLowerCase());
}

document.getElementById("addBookForm").addEventListener("submit", function (event) {
    event.preventDefault();

    const id = document.getElementById("bookId").value.trim();
    const title = document.getElementById("title").value.trim();
    const author = document.getElementById("author").value.trim();
    const pages = Number(document.getElementById("pages").value);

    if (!id || !title || !author || !pages) {
        showMessage("Please fill all book details.", "error");
        return;
    }

    if (findBook(id)) {
        showMessage("Book ID already exists.", "error");
        return;
    }

    books.push({
        id,
        title,
        author,
        pages,
        status: "AVAILABLE",
        borrowedBy: ""
    });

    renderBooks();
    this.reset();
    showMessage("Book added successfully.", "success");
});

document.getElementById("searchBtn").addEventListener("click", function () {
    const keyword = document.getElementById("searchInput").value.trim().toLowerCase();

    const results = books.filter(book => book.title.toLowerCase().includes(keyword));

    renderBooks(results);
    showMessage(results.length + " book(s) found.", "success");
});

document.getElementById("resetSearchBtn").addEventListener("click", function () {
    document.getElementById("searchInput").value = "";
    renderBooks();
    showMessage("Search reset.", "success");
});

document.getElementById("borrowBtn").addEventListener("click", function () {
    const bookId = document.getElementById("borrowBookId").value.trim();
    const memberName = document.getElementById("memberName").value.trim();

    if (!bookId || !memberName) {
        showMessage("Please enter book ID and member name.", "error");
        return;
    }

    const book = findBook(bookId);

    if (!book) {
        showMessage("Book not found.", "error");
        return;
    }

    if (book.status === "BORROWED") {
        showMessage("Book is already borrowed.", "error");
        return;
    }

    book.status = "BORROWED";
    book.borrowedBy = memberName;

    history.push({
        bookId: book.id,
        member: memberName,
        action: "BORROWED"
    });

    renderBooks();
    renderHistory();
    showMessage("Book borrowed successfully.", "success");
});

document.getElementById("returnBtn").addEventListener("click", function () {
    const bookId = document.getElementById("returnBookId").value.trim();

    if (!bookId) {
        showMessage("Please enter book ID.", "error");
        return;
    }

    const book = findBook(bookId);

    if (!book) {
        showMessage("Book not found.", "error");
        return;
    }

    if (book.status === "AVAILABLE") {
        showMessage("Book is already available.", "error");
        return;
    }

    const previousBorrower = book.borrowedBy;

    book.status = "AVAILABLE";
    book.borrowedBy = "";

    history.push({
        bookId: book.id,
        member: previousBorrower,
        action: "RETURNED"
    });

    renderBooks();
    renderHistory();
    showMessage("Book returned successfully.", "success");
});

renderBooks();
renderHistory();
