const API = "http://localhost:8080";

async function getProducts() {
    const res = await fetch(`${API}/products`);
    return res.json();
}

async function getCategories() {
    const res = await fetch(`${API}/categories`);
    return res.json();
}

async function getProduct(id) {
    const res = await fetch(`${API}/products/${id}`);
    return res.json();
}

async function login(username, password) {
    const res = await fetch(`${API}/users`);
    const users = await res.json();

    const user = users.find(u => u.username === username && u.password === password);

    if (user) {
        localStorage.setItem("user", JSON.stringify(user));
        return true;
    }

    return false;
}