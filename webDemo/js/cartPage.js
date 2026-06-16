function loadCart() {
    const container = document.getElementById("cart");
    const cart = getCart();

    container.innerHTML = "";

    let total = 0;

    cart.forEach((p, i) => {
        total += p.price || 0;

        const div = document.createElement("div");
        div.className = "product";

        div.innerHTML = `
      <img src="${p.image || 'https://via.placeholder.com/120'}" />

      <div>
        <h3>${p.name}</h3>
        <p>${p.price} €</p>

        <button onclick="removeFromCart(${i}); location.reload()">
          Supprimer
        </button>
      </div>
    `;

        container.appendChild(div);
    });

    const totalDiv = document.createElement("div");
    totalDiv.className = "total";
    totalDiv.innerHTML = `Total: ${total} <span>&#8364;</span> `;

    container.appendChild(totalDiv);
}

loadCart();