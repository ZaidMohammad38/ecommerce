# 🛒 Spring Boot E-Commerce Backend Project

## 📌 Project Overview

This project is a complete E-Commerce Backend Application developed using:

* Spring Boot
* Spring MVC
* Spring Data JPA
* Hibernate
* MySQL
* Swagger OpenAPI
* Razorpay Payment Gateway
* Docker
* Java Mail Sender

The application provides REST APIs for managing:

* Categories
* Products
* Users
* Orders
* Reviews
* Wishlist
* Cart
* Image Upload
* Payments
* Email Sending

This project is designed to simulate a real-world e-commerce backend system.

---

# 🚀 Features

## ✅ User Features

* User Registration
* User Login
* Product Browsing
* Product Search
* Pagination & Sorting
* Add to Cart
* Wishlist Management
* Place Orders
* Add Reviews & Ratings
* Upload Product Images
* Email Notifications
* Online Payment Integration

---

## ✅ Admin Features

* Manage Categories
* Manage Products
* Manage Orders
* Update Order Status
* View Users
* Swagger API Documentation

---

# 🛠️ Technologies Used

| Technology       | Purpose                 |
| ---------------- | ----------------------- |
| Java 17          | Programming Language    |
| Spring Boot      | Backend Framework       |
| Spring MVC       | REST APIs               |
| Spring Data JPA  | Database Operations     |
| Hibernate        | ORM Framework           |
| MySQL            | Database                |
| Swagger OpenAPI  | API Documentation       |
| Razorpay         | Payment Gateway         |
| Docker           | Containerization        |
| Maven            | Dependency Management   |
| Lombok           | Reduce Boilerplate Code |
| Java Mail Sender | Email Sending           |

---

# 📁 Project Structure

```text
src/main/java/com/ecommerce
│
├── controller
│   ├── CategoryController.java
│   ├── ProductController.java
│   ├── UserController.java
│   ├── OrderController.java
│   ├── ReviewController.java
│   ├── WishlistController.java
│   ├── UploadController.java
│   ├── EmailController.java
│   └── PaymentController.java
│
├── entity
│   ├── Category.java
│   ├── Product.java
│   ├── User.java
│   ├── OrderEntity.java
│   ├── Review.java
│   ├── Wishlist.java
│   └── Cart.java
│
├── repository
│   ├── CategoryRepository.java
│   ├── ProductRepository.java
│   ├── UserRepository.java
│   ├── OrderRepository.java
│   ├── ReviewRepository.java
│   ├── WishlistRepository.java
│   └── CartRepository.java
│
├── service
│   ├── CategoryService.java
│   ├── ProductService.java
│   ├── UserService.java
│   ├── OrderService.java
│   ├── ReviewService.java
│   ├── WishlistService.java
│   ├── CartService.java
│   ├── FileUploadService.java
│   ├── EmailService.java
│   └── PaymentService.java
│
├── exception
│   ├── ErrorResponse.java
│   └── GlobalExceptionHandler.java
│
├── config
│   ├── OpenApiConfig.java
│   └── RazorpayConfig.java
│
└── EcommerceProjectApplication.java
```

---

# ⚙️ Setup Instructions

## ✅ Step 1: Clone Repository

```bash
git clone https://github.com/yourusername/ecommerce-project.git
```

---

## ✅ Step 2: Open Project

Open project in:

* Eclipse
* Spring Tool Suite (STS)
* IntelliJ IDEA
* VS Code

---

## ✅ Step 3: Configure MySQL

Create database:

```sql
CREATE DATABASE ecommerce;
```

---

## ✅ Step 4: Configure application.properties

```properties
spring.application.name=Ecommerce_project

server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Swagger
springdoc.api-docs.enabled=true
springdoc.swagger-ui.enabled=true

# File Upload
spring.servlet.multipart.enabled=true
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB

# Mail Configuration
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=yourgmail@gmail.com
spring.mail.password=your_app_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

# Razorpay
razorpay.key=your_key
razorpay.secret=your_secret
```

---

# 📦 Maven Dependencies

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>

<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.5.0</version>
</dependency>

<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>

<dependency>
    <groupId>com.razorpay</groupId>
    <artifactId>razorpay-java</artifactId>
    <version>1.4.4</version>
</dependency>
```

---

# ▶️ Running the Project

## ✅ Run Application

Run:

```text
EcommerceProjectApplication.java
```

OR

```bash
mvn spring-boot:run
```

---

# 📖 Swagger Documentation

## Swagger UI

```text
http://localhost:8080/swagger-ui/index.html
```

## OpenAPI Docs

```text
http://localhost:8080/v3/api-docs
```

---

# 📌 API Endpoints

# 🗂️ Category APIs

| Method | Endpoint             | Description        |
| ------ | -------------------- | ------------------ |
| POST   | /api/categories      | Add Category       |
| GET    | /api/categories      | Get All Categories |
| GET    | /api/categories/{id} | Get Category By Id |
| DELETE | /api/categories/{id} | Delete Category    |

---

# 📱 Product APIs

| Method | Endpoint             | Description       |
| ------ | -------------------- | ----------------- |
| POST   | /api/products        | Add Product       |
| GET    | /api/products        | Get All Products  |
| GET    | /api/products/{id}   | Get Product By Id |
| DELETE | /api/products/{id}   | Delete Product    |
| GET    | /api/products/search | Search Products   |

---

# 👤 User APIs

| Method | Endpoint            | Description   |
| ------ | ------------------- | ------------- |
| POST   | /api/users/register | Register User |
| POST   | /api/users/login    | User Login    |
| GET    | /api/users          | Get All Users |

---

# 🛒 Cart APIs

| Method | Endpoint           | Description      |
| ------ | ------------------ | ---------------- |
| POST   | /api/cart          | Add To Cart      |
| GET    | /api/cart/{userId} | Get User Cart    |
| DELETE | /api/cart/{id}     | Remove Cart Item |

---

# 📦 Order APIs

| Method | Endpoint              | Description         |
| ------ | --------------------- | ------------------- |
| POST   | /api/orders           | Place Order         |
| GET    | /api/orders           | Get All Orders      |
| GET    | /api/orders/user/{id} | Get User Orders     |
| PUT    | /api/orders/{id}      | Update Order Status |

---

# ❤️ Wishlist APIs

| Method | Endpoint               | Description     |
| ------ | ---------------------- | --------------- |
| POST   | /api/wishlist          | Add Wishlist    |
| GET    | /api/wishlist/{userId} | Get Wishlist    |
| DELETE | /api/wishlist/{id}     | Remove Wishlist |

---

# ⭐ Review APIs

| Method | Endpoint                         | Description    |
| ------ | -------------------------------- | -------------- |
| POST   | /api/reviews                     | Add Review     |
| GET    | /api/reviews/{productId}         | Get Reviews    |
| GET    | /api/reviews/average/{productId} | Average Rating |
| DELETE | /api/reviews/{id}                | Delete Review  |

---

# 🖼️ Upload APIs

| Method | Endpoint    | Description  |
| ------ | ----------- | ------------ |
| POST   | /api/upload | Upload Image |

---

# 📧 Email APIs

| Method | Endpoint   | Description |
| ------ | ---------- | ----------- |
| POST   | /api/email | Send Email  |

---

# 💳 Payment APIs

| Method | Endpoint                  | Description           |
| ------ | ------------------------- | --------------------- |
| POST   | /api/payment/create-order | Create Razorpay Order |

---

# 🔍 Search, Pagination & Sorting

## Example

```text
GET /api/products/search?keyword=phone&page=0&size=5&sortBy=price
```

| Parameter | Description            |
| --------- | ---------------------- |
| keyword   | Product search keyword |
| page      | Page number            |
| size      | Records per page       |
| sortBy    | Sorting field          |

---

# 🖼️ Image Upload

## Upload Product Image

```text
POST /api/upload
```

Use:

```text
multipart/form-data
```

Access image:

```text
http://localhost:8080/images/filename.jpg
```

---

# 📧 Email Sending

## Example

```text
POST /api/email
```

Parameters:

```text
to
subject
text
```

---

# 💳 Razorpay Integration

## Create Payment Order

```text
POST /api/payment/create-order?amount=500
```

---

# 🐳 Docker Support

## Build Jar

```bash
mvn clean package
```

---

## Dockerfile

```dockerfile
FROM eclipse-temurin:17

WORKDIR /app

COPY target/Ecommerce_project-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
```

---

## Build Docker Image

```bash
docker build -t ecommerce-app .
```

---

## Run Docker Container

```bash
docker run -p 8080:8080 ecommerce-app
```

---

# 🧪 Testing APIs

APIs can be tested using:

* Swagger UI
* Postman
* Thunder Client

---

# ⚠️ Common Issues & Fixes

## Swagger 500 Error

### Cause

Circular entity relationships.

### Fix

Use:

```java
@JsonIgnoreProperties
```

---

## MySQL Connection Error

Check:

```properties
spring.datasource.username
spring.datasource.password
```

---

## File Upload Error

Enable multipart support.

---

## Razorpay Authentication Failed

Use correct API key and secret.

---

# 📚 Concepts Learned

* Spring Boot
* REST APIs
* CRUD Operations
* Spring MVC
* JPA & Hibernate
* Exception Handling
* Swagger Documentation
* Pagination & Sorting
* File Upload
* Email Integration
* Payment Gateway
* Docker
* Maven

---

# 🔮 Future Enhancements

* JWT Authentication
* Spring Security
* React Frontend
* Admin Dashboard
* Payment Verification
* Order Tracking
* Cloud Deployment
* AWS Deployment
* CI/CD Pipeline
* Redis Caching
* Microservices

---

# 👨‍💻 Author

## Mohd Khizar Rasheed

Associate Software Engineer | Full Stack Developer

### Skills

* Java
* Spring Boot
* HTML
* CSS
* JavaScript
* React.js
* MySQL
* Docker

---

# 📌 Git Commands

## Initial Push

```bash
git init

git add .

git commit -m "Initial commit for ecommerce backend project"

git branch -M main

git remote add origin https://github.com/yourusername/repository-name.git

git push -u origin main
```

---

# ⭐ Conclusion

This E-Commerce Backend Project demonstrates real-world backend development using Spring Boot.

The project includes:

✅ CRUD APIs
✅ Swagger Documentation
✅ Payment Integration
✅ Dockerization
✅ Email Sending
✅ File Upload
✅ Pagination & Sorting

This project can be further extended into a complete production-ready E-Commerce platform.
