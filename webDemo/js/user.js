const params = new URLSearchParams(window.location.search);
const username = params.get("username");

function safeJson(obj) {
    return JSON.stringify(obj).replace(/'/g, "\\'");
}

function initials(name) {
    if (!name) return "?";
    return name.trim().charAt(0).toUpperCase();
}

async function loadUserPage() {

    const profileEl = document.getElementById("vendorProfile");
    const productsEl = document.getElementById("vendorProducts");
    const countEl = document.getElementById("vendorProductCount");

    if (!username) {
        profileEl.innerHTML = `
            <div class="empty-state">
                Aucun nom d'utilisateur fourni dans l'URL.<br>
                Exemple : <code>user.html?username=johndoe</code>
            </div>
        `;
        return;
    }

    const user = await getUserByUsername(username);

    if (!user) {
        profileEl.innerHTML = `
            <div class="empty-state">
                Aucun utilisateur trouvé pour « ${username} ».
            </div>
        `;
        return;
    }

    document.title = `${user.username} · Yeya Informatique`;

    profileEl.innerHTML = `
        <div class="vendor-avatar">${initials(user.username)}</div>

        <div class="vendor-info">
            <h1>${user.username}</h1>

            ${user.description
        ? `<p class="vendor-description">${user.description}</p>`
        : `<p class="vendor-description muted">Ce vendeur n'a pas encore ajouté de description.</p>`
    }

            <div class="vendor-meta">
                ${user.email ? `<span>📧 ${user.email}</span>` : ""}
                ${user.type ? `<span class="badge">${user.type}</span>` : ""}
            </div>
        </div>
    `;

    const products = await getProductsByVendor(username);

    if (countEl) {
        countEl.textContent = products.length === 0
            ? "Aucun produit"
            : products.length === 1
                ? "1 produit"
                : `${products.length} produits`;
    }

    if (products.length === 0) {
        productsEl.innerHTML = `
            <p class="empty-state">Ce vendeur ne propose aucun produit pour le moment.</p>
        `;
        return;
    }

    productsEl.innerHTML = "";

    products.forEach(p => {

        const div = document.createElement("div");
        div.className = "product";

        div.innerHTML = `
            <img
                onclick="openProduct(${p.id})"
                src="${p.image ?? 'https://via.placeholder.com/300'}"
            >

            <h3>${p.name}</h3>

            <p>${p.description ?? ""}</p>

            <div class="price">${p.price ?? 0} €</div>

            <button onclick='addToCart(${safeJson(p)})'>
                Ajouter au panier
            </button>
        `;

        productsEl.appendChild(div);
    });
}

function openProduct(id) {
    window.location.href = `product.html?id=${id}`;
}

loadUserPage();
