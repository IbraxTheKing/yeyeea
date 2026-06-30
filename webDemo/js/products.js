let allProducts = [];
let selectedCategory = "all";
let selectedSort = "default";

/* =========================
   INIT
========================= */

window.addEventListener("DOMContentLoaded", () => {
    loadProducts();
});

/* =========================
   LOAD PRODUCTS
========================= */

async function loadProducts() {

    allProducts = await getProducts();

    renderProducts(allProducts);

    loadCategories();
}

/* =========================
   RENDER PRODUCTS
========================= */

function renderProducts(products) {

    const container = document.getElementById("products");

    container.innerHTML = "";

    if (!products || products.length === 0) {
        container.innerHTML = `<p class="empty-state">Aucun produit trouvé</p>`;
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

            <h3>${p.name}</h3>

            <p>${p.description ?? ""}</p>

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

    if (!select || !list) return;


    categories.forEach(c => {


        /* SIDEBAR */
        const li = document.createElement("li");
        li.textContent = c.name;
        li.onclick = () => filterCategory(c.name);
        list.appendChild(li);
    });
}

/* =========================
   CATEGORY FILTER
========================= */

function filterCategory(category) {

    selectedCategory = category;

    document.getElementById("categoryFilter").value = category;

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
   FILTERS (CATEGORY + SORT)
========================= */

function applyFilters() {

    let result = [...allProducts];

    /* CATEGORY */
    if (selectedCategory !== "all") {
        result = result.filter(p =>
            p.category?.name === selectedCategory
        );
    }

    /* SORT PRICE */
    const priceSort = document.getElementById("priceFilter")?.value;

    if (priceSort === "asc") {
        result.sort((a, b) =>
            Number(a.price ?? 0) - Number(b.price ?? 0)
        );
    }

    if (priceSort === "desc") {
        result.sort((a, b) =>
            Number(b.price ?? 0) - Number(a.price ?? 0)
        );
    }

    renderProducts(result);
}

/* =========================
   PRICE RANGE (BACKEND)
========================= */

async function applyPriceRange() {

    const min = document.getElementById("minPrice")?.value || 0;
    const max = document.getElementById("maxPrice")?.value || 999999;

    const products = await getProductsByPriceRange(min, max);

    renderProducts(products);
}

/* =========================
   OPEN PRODUCT PAGE
========================= */

function openProduct(id) {
    window.location.href = `product.html?id=${id}`;
}

/* =========================
   INIT ALL FILTERS (OPTION)
========================= */

function applyAllFilters() {
    applyFilters();
}