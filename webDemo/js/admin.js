async function loadAdmin(){


    const products =
        await getProducts();


    const div =
        document.getElementById("adminProducts");


    div.innerHTML="";


    products.forEach(p=>{


        div.innerHTML += `


<div class="product">


<img src="${p.image}">


<div>


<h3>${p.name}</h3>

<p>
${p.price} €
</p>


<button onclick="deleteProduct(${p.id})">
Supprimer
</button>


<button onclick="editProduct(${p.id})">
Modifier
</button>


</div>


</div>


`;


    });


}





async function createProduct(){


    const product={

        name:
        document.getElementById("name").value,


        price:
            Number(document.getElementById("price").value),


        image:
        document.getElementById("image").value,


        description:
        document.getElementById("description").value


    };


    await fetch(
        "http://localhost:8080/products",
        {
            method:"POST",

            headers:{
                "Content-Type":"application/json"
            },

            body:
                JSON.stringify(product)

        });


    alert("Produit ajouté");


    loadAdmin();

}




async function deleteProduct(id){


    await fetch(
        `http://localhost:8080/products/${id}`,
        {
            method:"DELETE"
        });


    loadAdmin();

}




async function editProduct(id){


    const name =
        prompt("Nouveau nom");


    await fetch(
        `http://localhost:8080/products/${id}`,
        {

            method:"PUT",

            headers:{
                "Content-Type":"application/json"
            },

            body:
                JSON.stringify({

                    id:id,

                    name:name

                })

        });


    loadAdmin();

}


loadAdmin();