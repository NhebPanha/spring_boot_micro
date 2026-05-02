# 🚀 Spring Micro Product Management System

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-Kotlin-green?style=for-the-badge)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue?style=for-the-badge)
![Status](https://img.shields.io/badge/Project-Active-success?style=for-the-badge)

---

## ✨ Overview

A modern **Product Management REST API** built with Spring Boot and Kotlin.  
This project follows a clean architecture pattern:

**Controller → Service → Repository → DTO → Mapper**

---

## 🎬 Animated Header

![Typing](https://readme-typing-svg.herokuapp.com?font=Fira+Code&size=20&pause=1000&color=00C853&width=600&lines=Spring+Boot+Product+API;Kotlin+Backend+Development;Clean+Architecture;RESTful+API+System)

---

## 🏗️ Architecture



---

## 📦 Features

- Create Product
- Get All Products
- Get Product By ID
- Update Product
- Delete Product
- DTO Mapping
- PostgreSQL Integration
- Clean Code Architecture

---

## 🛠️ Tech Stack

| Layer        | Technology        |
|-------------|------------------|
| Backend      | Spring Boot      |
| Language     | Kotlin           |
| Database     | PostgreSQL       |
| ORM          | Spring Data JPA  |
| Validation   | Hibernate        |

---

## 📁 Project Structure



---

## 🌐 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST   | /api/products | Create Product |
| GET    | /api/products | Get All Products |
| GET    | /api/products/{id} | Get Product |
| PUT    | /api/products/{id} | Update Product |
| DELETE | /api/products/{id} | Delete Product |

---

## 📤 Sample Request JSON

```json
{
  "name": "Steel Bar 12mm",
  "description": "Strong steel bar for construction",
  "price": 12.5,
  "discountPrice": 11.0,
  "quantity": 100,
  "sku": "STEEL-12MM",
  "category": "Construction",
  "brand": "SteelPro",
  "imageUrl": "https://example.com/image.jpg",
  "weight": 12.0,
  "size": "12mm",
  "color": "Silver",
  "rating": 4.7,
  "isActive": true
}

