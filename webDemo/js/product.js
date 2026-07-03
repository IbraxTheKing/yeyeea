const params = new URLSearchParams(window.location.search);
const id = params.get("id");

/* =========================
   GO BACK BUTTON
========================= */

function goBack() {
    // si l'utilisateur vient d'une page du site → retour navigateur
    if (document.referrer && document.referrer.includes(window.location.host)) {
        history.back();
    } else {
        // fallback propre
        window.location.href = "index.html";
    }
}

/* =========================
   LOAD PRODUCT
========================= */

async function loadProduct() {

    const container = document.getElementById("productDetails");

    container.innerHTML = `
        <div class="product big skeleton">
            <div class="skeleton-block img"></div>
            <div style="flex:1">
                <div class="skeleton-block line short"></div>
                <div class="skeleton-block line"></div>
                <div class="skeleton-block line"></div>
            </div>
        </div>
    `;

    const product = await getProduct(id);

    if (!product) {
        container.innerHTML = `<div class="empty-state">Produit introuvable.</div>`;
        return;
    }

    container.innerHTML = `
        <div class="product big">

            <img src="${product.image ?? 'https://via.placeholder.com/300'}">

            <div>
                <h1>${product.name}</h1>
                <h2>${product.price} &#8364;</h2>

                ${product.category?.name
        ? `<span class="category-tag">${product.category.name}</span>`
        : ""
    }

                <p>${product.description}</p>

                ${product.vendor?.username
        ? `<p>
                        Vendu par :
                        <a class="vendor-link" href="user.html?username=${encodeURIComponent(product.vendor.username)}">
                            ${product.vendor.username}
                        </a>
                      </p>`
        : `<p>Entreprise : ${product.company ?? "Inconnue"}</p>`
    }

                <button onclick='addToCart(${JSON.stringify(product)})'>
                    Ajouter au panier
                </button>
            </div>
        </div>
    `;

    await loadRecommendations(product);
}

/* =========================
   RECOMMANDATIONS
========================= */

async function loadRecommendations(product) {

    const container = document.getElementById("productDetails");

    const recDiv = document.createElement("div");
    recDiv.className = "recommendations";

    recDiv.innerHTML = `
        <h3>Produits similaires</h3>
        <div class="recommendation-list" id="recommendationList"></div>
    `;

    container.appendChild(recDiv);

    const allProducts = await getProducts();

    const filtered = allProducts.filter(p => {
        if (!p || p.id === product.id) return false;

        const sameCategory =
            p.category?.name &&
            product.category?.name &&
            p.category.name === product.category.name;

        const sameBrand =
            (p.company && product.company && p.company === product.company) ||
            (p.vendor?.username && product.vendor?.username && p.vendor.username === product.vendor.username);

        return sameCategory || sameBrand;
    });

    const list = document.getElementById("recommendationList");

    if (filtered.length === 0) {
        list.innerHTML = `<p>Aucune recommandation disponible.</p>`;
        return;
    }

    list.innerHTML = filtered.slice(0, 6).map(p => `
        <a class="rec-card" href="product.html?id=${p.id}">
            <img src="${p.image ?? 'https://via.placeholder.com/150'}" />
            <div>
                <strong>${p.name}</strong>
                <p>${p.price} &#8364;</p>
            </div>
        </a>
    `).join("");
}

loadProduct();