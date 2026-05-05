const api = {
    products: "/api/products",
    categories: "/api/categories",
    cart: "/api/cart",
    wishlist: "/api/wishlist",
    orders: "/api/orders",
    users: "/api/users"
};

const state = {
    products: [],
    categories: [],
    cart: [],
    wishlist: [],
    orders: []
};

const els = {
    activeUserId: document.querySelector("#activeUserId"),
    productGrid: document.querySelector("#productGrid"),
    productCount: document.querySelector("#productCount"),
    searchInput: document.querySelector("#searchInput"),
    categoryFilter: document.querySelector("#categoryFilter"),
    cartList: document.querySelector("#cartList"),
    wishlistList: document.querySelector("#wishlistList"),
    orderList: document.querySelector("#orderList"),
    toast: document.querySelector("#toast")
};

document.addEventListener("DOMContentLoaded", () => {
    bindEvents();
    loadInitialData();
});

function bindEvents() {
    document.querySelector("#refreshBtn").addEventListener("click", loadInitialData);
    document.querySelector("#clearCartBtn").addEventListener("click", clearCart);
    document.querySelector("#loadOrdersBtn").addEventListener("click", loadOrders);
    document.querySelector("#registerBtn").addEventListener("click", registerUser);
    document.querySelector("#loginBtn").addEventListener("click", loginUser);
    document.querySelector("#categoryForm").addEventListener("submit", saveCategory);
    document.querySelector("#productForm").addEventListener("submit", saveProduct);
    document.querySelector("#orderForm").addEventListener("submit", placeOrder);
    els.searchInput.addEventListener("input", renderProducts);
    els.categoryFilter.addEventListener("change", loadProducts);
    els.activeUserId.addEventListener("change", () => {
        loadCart();
        loadWishlist();
        loadOrders();
    });
}

async function loadInitialData() {
    await Promise.all([loadCategories(), loadProducts()]);
    await Promise.all([loadCart(), loadWishlist(), loadOrders()]);
}

async function requestJson(url, options = {}) {
    const response = await fetch(url, {
        headers: {
            "Content-Type": "application/json",
            ...options.headers
        },
        ...options
    });

    if (!response.ok) {
        const message = await response.text();
        throw new Error(message || `Request failed with ${response.status}`);
    }

    const text = await response.text();
    try {
        return text ? JSON.parse(text) : null;
    } catch {
        return text;
    }
}

async function loadCategories() {
    try {
        state.categories = await requestJson(api.categories);
        renderCategoryFilter();
    } catch (error) {
        showError(error);
    }
}

async function loadProducts() {
    const categoryId = els.categoryFilter.value;
    const url = categoryId ? `${api.products}/category/${categoryId}` : api.products;

    try {
        state.products = await requestJson(url);
        renderProducts();
    } catch (error) {
        showError(error);
    }
}

async function loadCart() {
    try {
        state.cart = await requestJson(`${api.cart}/${getUserId()}`);
        renderList(els.cartList, state.cart, renderCartItem, "Your cart is empty.");
    } catch (error) {
        renderEmpty(els.cartList, "Cart unavailable.");
        showError(error);
    }
}

async function loadWishlist() {
    try {
        state.wishlist = await requestJson(`${api.wishlist}/${getUserId()}`);
        renderList(els.wishlistList, state.wishlist, renderWishlistItem, "No saved products yet.");
    } catch (error) {
        renderEmpty(els.wishlistList, "Wishlist unavailable.");
        showError(error);
    }
}

async function loadOrders() {
    try {
        state.orders = await requestJson(`${api.orders}/user/${getUserId()}`);
        renderList(els.orderList, state.orders, renderOrderItem, "No orders found for this user.");
    } catch (error) {
        renderEmpty(els.orderList, "Orders unavailable.");
        showError(error);
    }
}

function renderCategoryFilter() {
    const currentValue = els.categoryFilter.value;
    els.categoryFilter.innerHTML = `<option value="">All categories</option>`;
    state.categories.forEach((category) => {
        const option = document.createElement("option");
        option.value = category.id;
        option.textContent = category.name;
        els.categoryFilter.appendChild(option);
    });
    els.categoryFilter.value = currentValue;
}

function renderProducts() {
    const query = els.searchInput.value.trim().toLowerCase();
    const products = state.products.filter((product) => {
        return !query || product.name?.toLowerCase().includes(query);
    });

    els.productCount.textContent = `${products.length} ${products.length === 1 ? "item" : "items"}`;

    if (!products.length) {
        renderEmpty(els.productGrid, "No products match your filters.");
        return;
    }

    els.productGrid.innerHTML = products.map((product) => {
        const image = product.imageUrl || fallbackImage(product.name);
        const category = product.category?.name || "Uncategorized";
        const stock = product.stock ?? 0;

        return `
            <article class="product-card">
                <img class="product-image" src="${escapeHtml(image)}" alt="${escapeHtml(product.name || "Product")}">
                <div class="product-body">
                    <div class="product-title">
                        <h3>${escapeHtml(product.name || "Untitled product")}</h3>
                        <span class="price">${formatMoney(product.price)}</span>
                    </div>
                    <p class="description">${escapeHtml(product.description || "No description available.")}</p>
                    <div class="meta-row">
                        <span class="pill">ID ${product.id}</span>
                        <span class="pill">${escapeHtml(category)}</span>
                        <span class="pill">${stock} in stock</span>
                    </div>
                    <div class="card-actions">
                        <button type="button" onclick="addToCart(${product.id})">Add to Cart</button>
                        <button type="button" class="secondary" onclick="addToWishlist(${product.id})">Wishlist</button>
                    </div>
                </div>
            </article>
        `;
    }).join("");
}

function renderList(target, items, renderer, emptyMessage) {
    if (!items || !items.length) {
        renderEmpty(target, emptyMessage);
        return;
    }

    target.innerHTML = items.map(renderer).join("");
}

function renderCartItem(item) {
    return `
        <article class="list-item">
            <div>
                <h3>${escapeHtml(item.product?.name || "Product")}</h3>
                <p>Qty ${item.quantity || 1} · ${formatMoney(item.product?.price)}</p>
            </div>
            <button class="secondary" type="button" onclick="removeCartItem(${item.id})">Remove</button>
        </article>
    `;
}

function renderWishlistItem(item) {
    return `
        <article class="list-item">
            <div>
                <h3>${escapeHtml(item.product?.name || "Product")}</h3>
                <p>${formatMoney(item.product?.price)} · Product ID ${item.product?.id || "-"}</p>
            </div>
            <button class="secondary" type="button" onclick="removeWishlistItem(${item.id})">Remove</button>
        </article>
    `;
}

function renderOrderItem(order) {
    return `
        <article class="list-item">
            <div>
                <h3>${escapeHtml(order.product?.name || "Product")}</h3>
                <p>Order #${order.id} · Qty ${order.quantity || 1} · ${formatMoney(order.totalPrice)} · ${escapeHtml(order.status || "Pending")}</p>
            </div>
            <span class="pill">${escapeHtml(order.status || "Pending")}</span>
        </article>
    `;
}

function renderEmpty(target, message) {
    target.innerHTML = `<div class="empty-state">${escapeHtml(message)}</div>`;
}

async function addToCart(productId) {
    try {
        await requestJson(api.cart, {
            method: "POST",
            body: JSON.stringify({
                quantity: 1,
                user: { id: getUserId() },
                product: { id: productId }
            })
        });
        showToast("Added to cart.");
        loadCart();
    } catch (error) {
        showError(error);
    }
}

async function addToWishlist(productId) {
    try {
        await requestJson(api.wishlist, {
            method: "POST",
            body: JSON.stringify({
                user: { id: getUserId() },
                product: { id: productId }
            })
        });
        showToast("Added to wishlist.");
        loadWishlist();
    } catch (error) {
        showError(error);
    }
}

async function removeCartItem(cartId) {
    try {
        await requestJson(`${api.cart}/item/${cartId}`, { method: "DELETE" });
        showToast("Cart item removed.");
        loadCart();
    } catch (error) {
        showError(error);
    }
}

async function removeWishlistItem(wishlistId) {
    try {
        await requestJson(`${api.wishlist}/${wishlistId}`, { method: "DELETE" });
        showToast("Wishlist item removed.");
        loadWishlist();
    } catch (error) {
        showError(error);
    }
}

async function clearCart() {
    try {
        await requestJson(`${api.cart}/clear/${getUserId()}`, { method: "DELETE" });
        showToast("Cart cleared.");
        loadCart();
    } catch (error) {
        showError(error);
    }
}

async function registerUser() {
    const user = {
        name: document.querySelector("#registerName").value,
        mobile: document.querySelector("#registerMobile").value,
        email: document.querySelector("#registerEmail").value,
        password: document.querySelector("#registerPassword").value
    };

    try {
        const saved = await requestJson(`${api.users}/register`, {
            method: "POST",
            body: JSON.stringify(user)
        });
        if (saved?.id) {
            els.activeUserId.value = saved.id;
        }
        showToast("User registered.");
    } catch (error) {
        showError(error);
    }
}

async function loginUser() {
    try {
        const message = await requestJson(`${api.users}/login`, {
            method: "POST",
            body: JSON.stringify({
                email: document.querySelector("#registerEmail").value,
                password: document.querySelector("#registerPassword").value
            })
        });
        showToast(String(message || "Login request complete."));
    } catch (error) {
        showError(error);
    }
}

async function saveCategory(event) {
    event.preventDefault();
    const category = {
        name: document.querySelector("#categoryName").value,
        description: document.querySelector("#categoryDescription").value
    };

    try {
        const saved = await requestJson(api.categories, {
            method: "POST",
            body: JSON.stringify(category)
        });
        document.querySelector("#productCategoryId").value = saved.id || "";
        event.target.reset();
        showToast("Category saved.");
        loadCategories();
    } catch (error) {
        showError(error);
    }
}

async function saveProduct(event) {
    event.preventDefault();
    const product = {
        name: document.querySelector("#productName").value,
        price: Number(document.querySelector("#productPrice").value),
        stock: Number(document.querySelector("#productStock").value),
        imageUrl: document.querySelector("#productImageUrl").value,
        description: document.querySelector("#productDescription").value,
        category: {
            id: Number(document.querySelector("#productCategoryId").value)
        }
    };

    try {
        await requestJson(api.products, {
            method: "POST",
            body: JSON.stringify(product)
        });
        event.target.reset();
        showToast("Product saved.");
        loadProducts();
    } catch (error) {
        showError(error);
    }
}

async function placeOrder(event) {
    event.preventDefault();
    const productId = Number(document.querySelector("#orderProductId").value);
    const quantity = Number(document.querySelector("#orderQuantity").value);
    const product = state.products.find((item) => item.id === productId);

    try {
        await requestJson(api.orders, {
            method: "POST",
            body: JSON.stringify({
                quantity,
                totalPrice: Number(product?.price || 0) * quantity,
                status: "PLACED",
                user: { id: getUserId() },
                product: { id: productId }
            })
        });
        event.target.reset();
        showToast("Order placed.");
        loadOrders();
    } catch (error) {
        showError(error);
    }
}

function getUserId() {
    return Number(els.activeUserId.value || 1);
}

function formatMoney(value) {
    return new Intl.NumberFormat("en-IN", {
        style: "currency",
        currency: "INR",
        maximumFractionDigits: 2
    }).format(Number(value || 0));
}

function fallbackImage(name = "product") {
    const text = encodeURIComponent(name);
    return `https://placehold.co/800x600/f0ede5/20211f?text=${text}`;
}

function escapeHtml(value = "") {
    return String(value)
        .replaceAll("&", "&amp;")
        .replaceAll("<", "&lt;")
        .replaceAll(">", "&gt;")
        .replaceAll('"', "&quot;")
        .replaceAll("'", "&#039;");
}

function showToast(message) {
    els.toast.textContent = message;
    els.toast.classList.add("show");
    window.clearTimeout(showToast.timeout);
    showToast.timeout = window.setTimeout(() => {
        els.toast.classList.remove("show");
    }, 2600);
}

function showError(error) {
    console.error(error);
    showToast(error.message || "Something went wrong.");
}
