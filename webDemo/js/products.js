let allProducts = [];



async function loadProducts(){


    allProducts = await getProducts();


    renderProducts(allProducts);


    loadCategories();


}





function renderProducts(products){


    const container =
        document.getElementById("products");


    container.innerHTML="";


    products.forEach(p=>{


        const div =
            document.createElement("div");


        div.className="product";



        div.innerHTML = `

        <img
        onclick="openProduct(${p.id})"
        src="
        ${p.image ??
        'https://via.placeholder.com/300'}
        ">


        <h3>
        ${p.name}
        </h3>


        <p>
        ${p.description ?? ""}
        </p>


        <div class="price">
        ${p.price ?? 0} <span>&#8364;</span> 
        </div>


        <button onclick='addToCart(${JSON.stringify(p)})'>
            Ajouter au panier
        </button>

        `;


        container.appendChild(div);



    });


}





function loadCategories(){


    const select =
        document.getElementById("categoryFilter");



    const categories =
        [
            ...new Set(
                allProducts
                    .map(p=>p.category?.name)
                    .filter(Boolean)
            )
        ];



    categories.forEach(c=>{


        let option =
            document.createElement("option");


        option.value=c;

        option.textContent=c;


        select.appendChild(option);



    });


}






function applyFilters(){


    let result =
        [...allProducts];



    /*
     FILTRE CATEGORIE
    */


    const category =
        document.getElementById("categoryFilter").value;



    if(category !== "all"){


        result =
            result.filter(
                p =>
                    p.category?.name === category
            );


    }




    /*
     TRI PRIX
    */


    const price =
        document.getElementById("priceFilter").value;



    if(price === "asc"){


        result.sort(
            (a,b)=>
                (a.price ?? 0)
                -
                (b.price ?? 0)
        );


    }



    if(price === "desc"){


        result.sort(
            (a,b)=>
                (b.price ?? 0)
                -
                (a.price ?? 0)
        );


    }




    renderProducts(result);



}





function openProduct(id){

    window.location.href =
        `product.html?id=${id}`;

}





loadProducts();