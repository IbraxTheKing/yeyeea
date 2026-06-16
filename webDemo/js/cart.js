function getCart() {
    return JSON.parse(localStorage.getItem("cart") || "[]");
}

function addToCart(product) {
    const cart = getCart();
    cart.push(product);
    localStorage.setItem("cart", JSON.stringify(cart));
    alert("Ajouté au panier");
}

function removeFromCart(index) {
    const cart = getCart();
    cart.splice(index, 1);
    localStorage.setItem("cart", JSON.stringify(cart));
}

/* 💳 FAKE PAY */
function payCart() {
    const cart = getCart();

    if (cart.length === 0) {
        alert("Panier vide");
        return;
    }

    const total = cart.reduce((sum, p) => sum + (p.price || 0), 0);

    alert(`💳 Paiement effectué (fake)\nTotal: ${total} <span>&#8364;</span> `);

    localStorage.removeItem("cart");
}