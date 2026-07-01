let allProducts = [];
let selectedCategory = "all";

/* =========================
   INIT
========================= */

window.addEventListener("DOMContentLoaded", () => {
    loadProducts();
    wireFilterControls();
});

/* =========================
   LOAD PRODUCTS
========================= */

async function loadProducts() {

    const container = document.getElementById("products");
    if (container) {
        container.innerHTML = renderSkeletons(6);
    }

    allProducts = await getProducts();

    applyFilters();

    loadCategories();
}

/* =========================
   LOADING SKELETON
========================= */

function renderSkeletons(count) {
    return Array.from({length: count})
        .map(() => `
            <div class="product skeleton">
                <div class="skeleton-block img"></div>
                <div class="skeleton-block line short"></div>
                <div class="skeleton-block line"></div>
                <div class="skeleton-block line price"></div>
            </div>
        `)
        .join("");
}

/* =========================
   RENDER PRODUCTS
========================= */

function renderProducts(products) {

    const container = document.getElementById("products");

    container.innerHTML = "";

    updateResultsCount(products.length);

    if (!products || products.length === 0) {
        container.innerHTML = `
            <div class="empty-state">
                <p>Aucun produit ne correspond à votre recherche</p>
                <button class="secondary" onclick="resetFilters()">Réinitialiser les filtres</button>
            </div>
        `;
        return;
    }

    products.forEach(p => {

        const div = document.createElement("div");
        div.className = "product";

        div.innerHTML = `

            <img
                onclick="openProduct(${p.id})"
                src="${p.image ?? 'https://via.placeholder.com/300'}"
            >

            ${p.category?.name
            ? `<span class="category-tag">${p.category.name}</span>`
            : ""
        }

            <h3>${p.name}</h3>

            <p>${p.description ?? ""}</p>

            ${p.vendor?.username
            ? `<a class="vendor-link" href="user.html?username=${encodeURIComponent(p.vendor.username)}">
                    par ${p.vendor.username}
                   </a>`
            : ""
        }

            <div class="price">
                ${p.price ?? 0} €
            </div>

            <button onclick='addToCart(${safeJson(p)})'>
                Ajouter au panier
            </button>

        `;

        container.appendChild(div);
    });
}

function updateResultsCount(n) {
    const el = document.getElementById("resultsCount");
    if (!el) return;

    el.textContent = n === 0
        ? "Aucun résultat"
        : n === 1
            ? "1 produit"
            : `${n} produits`;
}

/* =========================
   SAFE JSON (IMPORTANT)
========================= */

function safeJson(obj) {
    return JSON.stringify(obj).replace(/'/g, "\\'");
}

/* =========================
   CATEGORIES (REST API)
========================= */

async function loadCategories() {

    const categories = await getCategories();

    const select = document.getElementById("categoryFilter");
    const list = document.getElementById("categoryList");

    if (select) {
        select.innerHTML = `<option value="all">Toutes les catégories</option>`;

        categories.forEach(c => {
            const opt = document.createElement("option");
            opt.value = c.name;
            opt.textContent = c.name;
            select.appendChild(opt);
        });
    }

    if (list) {
        // on garde le "Toutes" déjà présent dans le HTML, on ajoute le reste
        categories.forEach(c => {
            const li = document.createElement("li");
            li.textContent = c.name;
            li.onclick = () => filterCategory(c.name);
            list.appendChild(li);
        });
    }

    updateSidebarActive();
}

/* =========================
   CATEGORY FILTER
========================= */

function filterCategory(category) {

    selectedCategory = category;

    const select = document.getElementById("categoryFilter");
    if (select) select.value = category;

    updateSidebarActive();

    applyFilters();
}

/* dropdown change */
function onDropdownCategoryChange() {

    selectedCategory = document.getElementById("categoryFilter").value;

    updateSidebarActive();

    applyFilters();
}

/* sidebar active UI */
function updateSidebarActive() {

    document.querySelectorAll("#categoryList li").forEach(li => {

        li.classList.remove("active");

        if (
            (selectedCategory === "all" && li.textContent === "Toutes") ||
            li.textContent === selectedCategory
        ) {
            li.classList.add("active");
        }
    });
}

/* =========================
   FILTERS (CATEGORY + SORT + PRICE)
========================= */

function wireFilterControls() {

    const sortSelect = document.getElementById("sortFilter");
    if (sortSelect) {
        sortSelect.addEventListener("change", applyFilters);
    }

    const priceRange = document.getElementById("priceRange");
    const priceMaxLabel = document.getElementById("priceMax");

    if (priceRange && priceMaxLabel) {
        priceMaxLabel.textContent = `${priceRange.value}€`;

        priceRange.addEventListener("input", () => {
            priceMaxLabel.textContent = `${priceRange.value}€`;
        });

        priceRange.addEventListener("change", applyFilters);
    }
}

function applyFilters() {

    let result = [...allProducts];

    /* CATEGORY */
    if (selectedCategory !== "all") {
        result = result.filter(p =>
            p.category?.name === selectedCategory
        );
    }

    /* PRICE (client-side, slider) */
    const priceRange = document.getElementById("priceRange");
    if (priceRange) {
        const max = Number(priceRange.value);
        result = result.filter(p => Number(p.price ?? 0) <= max);
    }

    /* SORT */
    const sort = document.getElementById("sortFilter")?.value;

    if (sort === "price-asc") {
        result.sort((a, b) => Number(a.price ?? 0) - Number(b.price ?? 0));
    }

    if (sort === "price-desc") {
        result.sort((a, b) => Number(b.price ?? 0) - Number(a.price ?? 0));
    }

    if (sort === "name-asc") {
        result.sort((a, b) => (a.name ?? "").localeCompare(b.name ?? ""));
    }

    renderProducts(result);
}

function resetFilters() {

    selectedCategory = "all";

    const select = document.getElementById("categoryFilter");
    if (select) select.value = "all";

    const sortSelect = document.getElementById("sortFilter");
    if (sortSelect) sortSelect.value = "default";

    const priceRange = document.getElementById("priceRange");
    const priceMaxLabel = document.getElementById("priceMax");
    if (priceRange) {
        priceRange.value = priceRange.max;
        if (priceMaxLabel) priceMaxLabel.textContent = `${priceRange.value}€`;
    }

    updateSidebarActive();
    applyFilters();
}

/* =========================
   OPEN PRODUCT PAGE
========================= */

function openProduct(id) {
    window.location.href = `product.html?id=${id}`;
}
