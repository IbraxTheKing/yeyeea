let allProductsCache = [];

/* =========================
   INIT GLOBAL
========================= */

window.addEventListener("DOMContentLoaded", async () => {
    try {
        allProductsCache = await getProducts();

        initFilters();
        initSearch();

    } catch (e) {
        console.error("Erreur chargement produits :", e);
    }
});

/* =========================
   CATEGORIES
========================= */

function initFilters() {
    const categorySelect = document.getElementById("filterCategory");

    if (!categorySelect) return;

    // sécurité : retry si produits pas prêts
    if (!allProductsCache || allProductsCache.length === 0) {
        setTimeout(initFilters, 200);
        return;
    }

    const categories = [
        ...new Set(
            allProductsCache
                .map(p => p.category?.name)
                .filter(Boolean)
        )
    ];

    categorySelect.innerHTML = `<option value="all">Toutes catégories</option>`;

    categories.forEach(cat => {
        const option = document.createElement("option");
        option.value = cat;
        option.textContent = cat;
        categorySelect.appendChild(option);
    });
}

/* =========================
   SEARCH CORE
========================= */

function initSearch() {
    console.log("initSearch");
    const input = document.getElementById("searchInput");
    const panel = document.getElementById("searchPanel");
    const resultsBox = document.getElementById("searchResults");
    const categorySelect = document.getElementById("filterCategory");
    const priceSelect = document.getElementById("filterPrice");

    // 🚨 SAFE GUARD (IMPORTANT)
    if (!input || !panel || !resultsBox) {
        console.warn("Search UI manquant dans le HTML");
        return;
    }

    function openPanel() {
        panel.style.display = "block";
    }

    function closePanel() {
        panel.style.display = "none";
    }

    function filterProducts() {
        const query = input.value?.trim().toLowerCase() || "";
        const category = categorySelect?.value || "all";
        const price = priceSelect?.value || "all";

        return allProductsCache.filter(p => {

            const matchText =
                !query ||
                p.name?.toLowerCase().includes(query) ||
                p.category?.name?.toLowerCase().includes(query) ||
                p.company?.toLowerCase().includes(query);

            const matchCategory =
                category === "all" ||
                p.category?.name === category;

            const priceValue = Number(p.price);

            const matchPrice =
                price === "all" ||
                (price === "0-50" && priceValue <= 50) ||
                (price === "50-200" && priceValue > 50 && priceValue <= 200) ||
                (price === "200-500" && priceValue > 200 && priceValue <= 500) ||
                (price === "500+" && priceValue > 500);

            return matchText && matchCategory && matchPrice;
        }).slice(0, 8);
    }

    function render() {
        console.log("render appelé");
        const results = filterProducts();

        if (results.length === 0) {
            resultsBox.innerHTML = `<div class="search-item">Aucun résultat</div>`;
            return;
        }

        resultsBox.innerHTML = results.map(p => `
            <a class="search-item" href="product.html?id=${p.id}">
                <img src="${p.image ?? 'https://via.placeholder.com/40'}">
                <div>
                    <strong>${p.name}</strong>
                    <small>${p.price} €</small>
                </div>
            </a>
        `).join("");
    }

    // EVENTS SAFE
    input.addEventListener("focus", () => {
        openPanel();
        render();
    });

    input.addEventListener("input", () => {
        openPanel();
        render();
    });

    categorySelect?.addEventListener("change", () => {
        openPanel();
        render();
    });

    priceSelect?.addEventListener("change", () => {
        openPanel();
        render();
    });

    document.addEventListener("click", (e) => {
        if (!e.target.closest("#searchBox")) {
            closePanel();
        }
    });
}

