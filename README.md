# ✈️ Flight Booking API (Spring Boot)

A simple in-memory Flight Booking REST API built using **Spring Boot (Java 17)** that demonstrates:

- REST API design
- Validation
- Exception handling
- Thread safety
- Idempotency
- Clean code practices
---

## 🚀 Features Implemented

### ✅ Flight Booking API
- Endpoint: `POST /api/bookings`
- Books a seat for a given flight
- Prevents overbooking

---

### ✅ In-Memory Data Store
- Flights stored using `ConcurrentHashMap`
- Example flights:
    - `AB123` → capacity 2
    - `CD456` → capacity 5

---

### ✅ Validation
- `flightNumber` → must not be blank
- `passengerName` → must not be blank

---

### ✅ Case-Insensitive Flight Lookup
- Handles `cd456`, `CD456`, etc.
- Normalized using `toUpperCase()`

---

### ✅ Thread Safety
- `synchronized` booking method prevents race conditions
- Ensures no overbooking in concurrent requests

---

### ✅ Proper Exception Handling
- Global exception handler using `@ControllerAdvice`

| Scenario              | Status Code |
|----------------------|------------|
| Validation error     | 400        |
| Flight not found     | 400        |
| Overbooking          | 409        |

---

### ✅ Enhanced Response
Each booking returns:

- `bookingId` (UUID)
- `flightNumber`
- `passengerName`
- `totalCapacity`
- `bookedSeats`
- `remainingCapacity`
- `bookedAt` (timestamp)

---

### ✅ Lombok Usage
- Removed boilerplate getters/setters
- Cleaner and more readable code

---

### ✅ Idempotency Support
- Prevents duplicate bookings on retries
- Uses `Idempotency-Key` header
- Same request → same response

---

## 📦 API Example

### 🔹 Request (cURL)

```bash
curl -X POST 'http://localhost:8080/api/bookings' \
--header 'Content-Type: application/json' \
--header 'Idempotency-Key: booking-123' \
--data '{
  "flightNumber": "CD456",
  "passengerName": "Suyash Mishra"
}'
```
## 🔹 Success Response (201 Created)
```json
{
  "bookingId": "8be6dc73-7745-4a8a-9002-5089d95e8448",
  "flightNumber": "CD456",
  "passengerName": "Suyash Mishra",
  "totalCapacity": 5,
  "bookedSeats": 3,
  "remainingCapacity": 2,
  "bookedAt": "2026-04-24T11:05:36.378478Z"
}

```
## 🚀 Possible Improvements

### 🔹 Persistence
- Use PostgreSQL / MySQL for durable storage  
- Store flights and bookings in a database  
- Introduce repository layer (Spring Data JPA)

---

### 🔹 Concurrency (Production-Grade)
- Replace `synchronized` with:
  - Optimistic locking (versioning)
  - Pessimistic locking (DB-level locks)
- Ensure safe concurrent bookings at scale  

---

### 🔹 Idempotency (Real System)
- Use Redis for distributed idempotency storage  
- Add TTL (expiry) for idempotency keys  
- Ensure atomic operations across multiple instances  

---

### 🔹 API Enhancements
- Add additional endpoints:
  - `GET /api/bookings/{id}` → fetch booking details  
  - `GET /api/flights` → list available flights  
- Add booking cancellation API  

---

### 🔹 Observability
- Add logging using SLF4J / Logback  
- Integrate metrics using Micrometer + Prometheus  
- Add tracing (OpenTelemetry) for request flow  

---

### 🔹 Validation Improvements
- Add custom validators (e.g., validate flight existence)  
- Standardize error response format (error code, message, timestamp)  
- Improve API error consistency  

---

## 🧪 How to Run

### ✅ Using Maven
```bash
./mvnw spring-boot:run
```

### ✅ Using Maven Wrapper (No Maven Installation Required)
```bash
./mvnw spring-boot:run
```

### ✅ Server should be running on 
```bash
http://localhost:8080