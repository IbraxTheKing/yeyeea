const params = new URLSearchParams(window.location.search);

const id = params.get("id");


async function loadProduct(){

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


<img src="
${product.image ??
    'https://via.placeholder.com/300'}
">


<div>

<h1>
${product.name}
</h1>


<h2>
${product.price} €
</h2>

${product.category?.name
        ? `<span class="category-tag">${product.category.name}</span>`
        : ""
    }

<p>
${product.description}
</p>


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

}


loadProduct();
