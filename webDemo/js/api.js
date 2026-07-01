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

async function getProductsByPriceRange(min, max) {
    const res = await fetch(`${API}/products/price-range/${min}/${max}`);
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

/* =========================
   VENDEUR / PAGE UTILISATEUR
========================= */

async function getUserByUsername(username) {
    const res = await fetch(`${API}/users/username/${encodeURIComponent(username)}`);

    if (!res.ok) return null;

    const users = await res.json();

    // le backend renvoie une liste (getByUsername), on prend le premier match
    if (Array.isArray(users)) {
        return users[0] ?? null;
    }

    return users ?? null;
}

async function getProductsByVendor(username) {
    // pas d'endpoint dédié côté backend : on filtre côté client
    // sur product.vendor.username (relation one-to-one Product -> User)
    const products = await getProducts();
    return products.filter(p => p.vendor?.username === username);
}
