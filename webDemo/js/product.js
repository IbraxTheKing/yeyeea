const params = new URLSearchParams(window.location.search);

const id = params.get("id");


async function loadProduct(){


    const product = await getProduct(id);


    document
        .getElementById("productDetails")
        .innerHTML = `


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


<p>
${product.description}
</p>


<p>
Entreprise :
${product.company ?? "Inconnue"}
</p>


<p>
Catégorie :
${product.category?.name ?? "Aucune"}
</p>


<button onclick='addToCart(${JSON.stringify(product)})'>
Ajouter au panier
</button>


</div>


</div>


`;

}


loadProduct();