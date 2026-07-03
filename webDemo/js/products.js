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
   SKELETON
========================= */

function renderSkeletons(count) {
    return Array.from({ length: count })
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
   DOMINANT COLOR (SAFE VERSION)
========================= */

function getDominantColor(img) {

    const canvas = document.createElement("canvas");
    const ctx = canvas.getContext("2d");

    try {
        canvas.width = img.naturalWidth || 100;
        canvas.height = img.naturalHeight || 100;

        ctx.drawImage(img, 0, 0);

        const data = ctx.getImageData(0, 0, canvas.width, canvas.height).data;

        let r = 0, g = 0, b = 0;
        let count = 0;

        for (let i = 0; i < data.length; i += 200) {
            r += data[i];
            g += data[i + 1];
            b += data[i + 2];
            count++;
        }

        r = Math.floor(r / count);
        g = Math.floor(g / count);
        b = Math.floor(b / count);

        return `rgb(${r},${g},${b})`;

    } catch (e) {
        console.error("Canvas blocked → CORS image problem", e);
        return null;
    }
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

        const imgUrl = p.image ?? "https://via.placeholder.com/300";

        div.innerHTML = `
            <img
                src="${imgUrl}"
                crossorigin="anonymous"
            >

            ${p.category?.name ? `<span class="category-tag">${p.category.name}</span>` : ""}

            <h3>${p.name}</h3>
            <p>${p.description ?? ""}</p>

            ${p.vendor?.username
            ? `<a class="vendor-link" href="user.html?username=${encodeURIComponent(p.vendor.username)}">
                    par ${p.vendor.username}
                   </a>`
            : ""
        }

            <div class="price">${p.price ?? 0} €</div>

            <button onclick='addToCart(${safeJson(p)})'>Ajouter au panier</button>
        `;

        container.appendChild(div);

        /* =========================
           CLICK FIX (IMPORTANT)
        ========================= */
        div.addEventListener("click", (e) => {
            // éviter clic bouton panier
            if (e.target.tagName === "BUTTON") return;
            openProduct(p.id);
        });

        /* =========================
           COLOR EFFECT (SAFE + DEBUG)
        ========================= */

        const img = div.querySelector("img");

        img.onload = () => {

            const color = getDominantColor(img);

            if (!color) {
                console.log("❌ couleur impossible (CORS ou canvas)");
                return;
            }

            div.style.boxShadow =
                `0 20px 50px rgba(0,0,0,.15), 0 0 80px ${color}55`;

            div.style.border = `1px solid ${color}33`;

            div.style.background =
                `linear-gradient(145deg, ${color}15, white 70%)`;
        };

        // si déjà chargé
        if (img.complete) img.onload();
    });
}

/* =========================
   RESULTS COUNT
========================= */

function updateResultsCount(n) {
    const el = document.getElementById("resultsCount");
    if (!el) return;

    el.textContent =
        n === 0 ? "Aucun résultat"
            : n === 1 ? "1 produit"
                : `${n} produits`;
}

/* =========================
   SAFE JSON
========================= */

function safeJson(obj) {
    return JSON.stringify(obj).replace(/'/g, "\\'");
}

/* =========================
   CATEGORIES
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
   FILTERS
========================= */

function filterCategory(category) {
    selectedCategory = category;

    const select = document.getElementById("categoryFilter");
    if (select) select.value = category;

    updateSidebarActive();
    applyFilters();
}

function onDropdownCategoryChange() {
    selectedCategory = document.getElementById("categoryFilter").value;

    updateSidebarActive();
    applyFilters();
}

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
   FILTER ENGINE
========================= */

function wireFilterControls() {

    document.getElementById("sortFilter")
        ?.addEventListener("change", applyFilters);

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

    if (selectedCategory !== "all") {
        result = result.filter(p =>
            p.category?.name === selectedCategory
        );
    }

    const priceRange = document.getElementById("priceRange");
    if (priceRange) {
        const max = Number(priceRange.value);
        result = result.filter(p => Number(p.price ?? 0) <= max);
    }

    const sort = document.getElementById("sortFilter")?.value;

    if (sort === "price-asc") {
        result.sort((a, b) => a.price - b.price);
    }

    if (sort === "price-desc") {
        result.sort((a, b) => b.price - a.price);
    }

    if (sort === "name-asc") {
        result.sort((a, b) => (a.name ?? "").localeCompare(b.name ?? ""));
    }

    renderProducts(result);
}

/* =========================
   RESET
========================= */

function resetFilters() {

    selectedCategory = "all";

    document.getElementById("categoryFilter").value = "all";
    document.getElementById("sortFilter").value = "default";

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
   NAV
========================= */

function openProduct(id) {
    window.location.href = `product.html?id=${id}`;
}