# FeedStream

FeedStream is a full-stack RSS feed aggregator that delivers real-time news using a modern, scalable architecture. It leverages **Kotlin** on the Android frontend and **Go** with **PostgreSQL** on the backend, offering users a seamless and fast news consumption experience.

## 📱 Android App

### Features

- 🔄 Aggregates multiple RSS feeds into a unified daily news stream.
- 🧠 Implements **MVI clean architecture** for modular, maintainable, and testable code.
- ⚡ Integrates **Pager3** to efficiently handle paging and infinite scroll, improving loading speed by over 30%.
- 🌐 Utilizes **Retrofit** for optimized API communication.
- 💾 Uses **RoomDB** for offline access and local caching.
- 🔐 Dependency injection via **Dagger Hilt** ensures scalable architecture.

---

## 🔧 Backend

The backend is built in **Go (Golang)** and is available here:  
👉 [https://github.com/manas-yu/RSS-Feed-Aggregator](https://github.com/manas-yu/RSS-Feed-Aggregator)

### Backend Highlights

- 🔍 Fast and concurrent **XML scrapers** using **Goroutines** to fetch and parse articles from RSS feed links.
- 🚀 Boosts parsing and scraping performance by over 50% through parallelism.
- 🛣️ Lightweight HTTP routing with **Chi Router** for handling RESTful API endpoints efficiently.
- 📚 Database schema management using **Goose**, making migration tracking and rollback seamless.
- 🗃️ Utilizes **PostgreSQL** for robust and scalable data storage.
- 📡 Provides clean, structured APIs consumed by the Android frontend.

---

## 📦 Tech Stack

### Android
- Kotlin
- MVI + Clean Architecture
- Dagger Hilt
- RoomDB
- Retrofit
- Pager3

### Backend
- Go (Golang)
- Chi Router
- Goose (DB migrations)
- Goroutines
- PostgreSQL

---

