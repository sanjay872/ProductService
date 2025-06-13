### ✅ ProductService

# GadgetHive – Product Service

This is the **Product Microservice** for the GadgetHive platform. It handles all operations related to product listings.

## 🛠 Tech Stack
- **Spring Boot**
- **MySQL (AWS RDS)**
- **JPA/Hibernate**
- **REST APIs**
- **CI/CD**: Jenkins + AWS EC2

## 📦 Features
- List all available products
- View product by ID
- Products filtered by vendor
- Integrated with VendorService via service communication

## 🔧 Setup Instructions

### 1. Clone the repository
```bash
git clone https://github.com/sanjay872/ProductService.git
cd ProductService
```
### 2. Update application properties
```bash
spring.datasource.url=jdbc:mysql://<AWS-RDS-ENDPOINT>:3306/ProductService
spring.datasource.username=<your-db-user>
spring.datasource.password=<your-db-password>

spring.jpa.hibernate.ddl-auto=update
server.port=8081
```

### 3. Run locally
```bash
./mvnw spring-boot:run
```

API will be available at http://localhost:8081/.

## 🧪 API Endpoints
`GET /products` – Get all products

`GET /products/{id}` – Get a product by ID

`GET /products/vendor/{vendorId}` – Products by vendor

`POST /products` – Add a new product (Vendor only)

`DELETE /products/{id}` – Delete a product

## 🚀 Deployment
- CI/CD with Jenkins
- Deployed on AWS EC2
- Uses AWS RDS (MySQL)
