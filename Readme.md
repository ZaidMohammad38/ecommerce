# Ecommerce

A Spring Boot ecommerce application with REST APIs for users, admins, products, categories, carts, wishlists, reviews, and orders. The project also includes a simple static storefront dashboard served from `src/main/resources/static`.

## Tech Stack

- Java 17
- Spring Boot 4.0.5
- Spring Web MVC
- Spring Data JPA
- Thymeleaf
- MySQL
- Lombok
- Maven Wrapper

## Features

- User registration and login
- Admin registration and login
- Product and category management
- Product search and category filtering
- Cart and wishlist management
- Order placement and status updates
- Product reviews and average ratings
- Static browser UI for catalog, cart, wishlist, orders, and catalog management

## Project Structure

```text
src/main/java/com/project/ecommerce
+-- controller     # REST controllers and route definitions
+-- entity         # JPA entities
+-- exception      # Global exception handling
+-- repository     # Spring Data repositories
+-- service        # Business logic interfaces and implementations

src/main/resources
+-- application.properties
+-- static         # HTML, CSS, and JavaScript frontend
```

## Prerequisites

- Java 17 or newer
- MySQL running locally
- A MySQL user matching the credentials in `src/main/resources/application.properties`

Default database configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce?createDatabaseIfNotExist=true
spring.datasource.username=your username
spring.datasource.password=your password
spring.jpa.hibernate.ddl-auto=update
```

Update these values if your local MySQL credentials are different.

## Run Locally

From the project root:

```bash
./mvnw spring-boot:run
```

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

The application starts on:

```text
http://localhost:8080
```

Open the same URL in a browser to use the static storefront UI.

## Build

```bash
./mvnw clean package
```

On Windows PowerShell:

```powershell
.\mvnw.cmd clean package
```

## Run Tests

```bash
./mvnw test
```

On Windows PowerShell:

```powershell
.\mvnw.cmd test
```

## API Endpoints

### Users

| Method | Endpoint | Description |
| --- | --- | --- |
| POST | `/api/users/register` | Register a user |
| POST | `/api/users/login` | Login a user |

### Admins

| Method | Endpoint | Description |
| --- | --- | --- |
| POST | `/api/admin/register` | Register an admin |
| POST | `/api/admin/login` | Login an admin |

### Categories

| Method | Endpoint | Description |
| --- | --- | --- |
| POST | `/api/categories` | Create a category |
| GET | `/api/categories` | Get all categories |
| GET | `/api/categories/{id}` | Get a category by ID |
| DELETE | `/api/categories/{id}` | Delete a category |

### Products

| Method | Endpoint | Description |
| --- | --- | --- |
| POST | `/api/products` | Create a product |
| GET | `/api/products` | Get all products |
| GET | `/api/products/{id}` | Get a product by ID |
| PUT | `/api/products/{id}` | Update a product |
| GET | `/api/products/search/{name}` | Search products by name |
| GET | `/api/products/category/{categoryId}` | Get products by category |
| DELETE | `/api/products/{id}` | Delete a product |

### Cart

| Method | Endpoint | Description |
| --- | --- | --- |
| POST | `/api/cart` | Add an item to cart |
| GET | `/api/cart/{userId}` | Get a user's cart |
| DELETE | `/api/cart/item/{cartId}` | Remove an item from cart |
| DELETE | `/api/cart/clear/{userId}` | Clear a user's cart |

### Wishlist

| Method | Endpoint | Description |
| --- | --- | --- |
| POST | `/api/wishlist` | Add an item to wishlist |
| GET | `/api/wishlist/{userId}` | Get a user's wishlist |
| DELETE | `/api/wishlist/{id}` | Remove an item from wishlist |

### Orders

| Method | Endpoint | Description |
| --- | --- | --- |
| POST | `/api/orders` | Place an order |
| GET | `/api/orders` | Get all orders |
| GET | `/api/orders/user/{userId}` | Get orders by user |
| PUT | `/api/orders/{id}?status=PLACED` | Update order status |

### Reviews

| Method | Endpoint | Description |
| --- | --- | --- |
| POST | `/api/reviews` | Create a review |
| GET | `/api/reviews/{productId}` | Get reviews for a product |
| GET | `/api/reviews/average/{productId}` | Get average rating for a product |
| DELETE | `/api/reviews/{id}` | Delete a review |

## Example Requests

Create a category:

```bash
curl -X POST http://localhost:8080/api/categories \
  -H "Content-Type: application/json" \
  -d "{\"name\":\"Electronics\",\"description\":\"Devices and accessories\"}"
```

Create a product:

```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d "{\"name\":\"Headphones\",\"description\":\"Wireless headphones\",\"price\":1999,\"stock\":20,\"imageUrl\":\"https://example.com/headphones.jpg\",\"category\":{\"id\":1}}"
```

Place an order:

```bash
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d "{\"quantity\":1,\"totalPrice\":1999,\"status\":\"PLACED\",\"user\":{\"id\":1},\"product\":{\"id\":1}}"
```

## Notes

- Hibernate is configured with `ddl-auto=update`, so tables are created or updated automatically when the app starts.
- The frontend uses relative API URLs, so it works when served by the Spring Boot application at `http://localhost:8080`.
- The static storefront assumes at least one user ID exists or can be registered from the UI.
