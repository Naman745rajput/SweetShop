# 🍬 The Sweet Shop API

A full-stack e-commerce application for selling authentic sweets. This project demonstrates a secure REST API with inventory management, soft deletion, and role-based access control (RBAC).

## 🚀 Features
* **User & Admin Roles:** Secure registration and login (JWT / Basic Auth).
* **Inventory Management:** Real-time stock tracking. Purchase button disables when stock is 0.
* **Soft Delete:** Data is never lost; items are marked as "deleted" to preserve order history.
* **Search & Filter:** Find sweets by Name or Category.
* **Admin Dashboard:** Add, Edit, and Remove products.

## 🛠️ Tech Stack
* **Backend:** Java, Spring Boot 3, Spring Data JPA, Hibernate.
* **Database:** PostgreSQL (with Soft Delete logic).
* **Frontend:** Vanilla JavaScript, HTML5, CSS3 (Single Page Application).
* **Tools:** Maven, IntelliJ IDEA.

## ⚙️ Setup & Run
1.  **Database:** Ensure PostgreSQL is running and create a DB named `sweetshop`.
2.  **Config:** Update `application.properties` with your DB credentials.
3.  **Run:** Execute `SweetShopApplication.java`.
4.  **Access:** Open `http://localhost:8080` in your browser.

---

## 🤖 My AI Usage Report
*Required Section for AI Kata Submission*

### 1. How I Used AI
I utilized a Large Language Model (LLM) as a "Pair Programmer" throughout this project. My approach was to define the architectural requirements first and use AI to accelerate implementation details.

* **Boilerplate Generation:** I used AI to generate the initial entity classes (`Sweet`, `User`) and repository interfaces to save typing time.
* **Complex Logic Implementation:** I asked the AI for options on how to handle data deletion. It proposed "Soft Delete" vs "Hard Delete." I chose Soft Delete (`@SQLDelete`), and the AI provided the specific Hibernate annotations.
* **Debugging:** When I encountered a `JSON parse error` (sending "5.99" as an Integer), I pasted the stack trace into the AI. It correctly identified that my Frontend IDs were swapped and helped me fix the JavaScript mapping.
* **Refactoring:** I used AI to convert my static HTML list into a dynamic JavaScript rendering function (`loadSweets`) that handles both search results and full inventory lists.

### 2. Reflection on AI
Using AI significantly increased my development speed, particularly for frontend JavaScript which is not my primary strength. However, I learned that **AI requires supervision**.

For example, the AI initially hallucinated a `searchSweets` method in the Repository that didn't exist. I had to manually add the `@Query` annotation to fix the backend. This reinforced that while AI is a powerful tool for generation, understanding the underlying code is essential for integration and debugging.

---