# 🏛️ BACKEND ENGINEERING STANDARDS

> **Principles:** TDD-First, Layered Architecture, Clean Code.

---

## 🔄 1. MANDATORY TDD WORKFLOW
*AI must stop and request approval at each **[STOP]** point.*

### Phase 1: Blueprint (Design)
- Define API Contract: Endpoints, Methods, and **Java 21 Records** for DTOs.
- Outline Database Schema changes.
- **[STOP]** Wait for Architect approval.

### Phase 2: Test-First (Validation)
- Generate **Service Layer** Unit Tests.
- Coverage: Happy Path, Business Exceptions, Edge Cases.
- **[STOP]** Wait for Test Review. **No implementation allowed before this.**

### Phase 3: Implementation (Coding)
- Order: `Entity` → `Repository` → `Mapper` → `Service` → `Controller`.
- All code must satisfy Phase 2 tests 100%.

### Phase 4: Refactor (Polish)


---

## 🏛️ 2. LAYERED ARCHITECTURE DEFINITION
| Layer | Responsibility | Strict Rules |
| :--- | :--- | :--- |
| **`controller`** | API Entry point | No logic. Return `ApiResponse<T>`. |
| **`service`** | Business Logic | Handles DTOs. `@Transactional`. TDD-driven. |
| **`repository`** | Data Access | Spring Data JPA. Optimization-focused. |
| **`dto`** | Data Carrier | **Java 21 Record** only. Separate In/Out. |
| **`mapper`** | Conversion | Dedicated classes. No manual mapping in Service. |
| **`entity`** | DB Mapping | Use `@Data`. |
| **`helper`** | Domain Support | Complex domain calculations (Non-static). |
| **`utils`** | Generic Tools | Static pure functions. Private constructor. |

---

## 📜 3. TECHNICAL CONVENTIONS
- **Dependency Injection:** Constructor Injection only via `@RequiredArgsConstructor`.
- **Validation:** Strict `jakarta.validation` in DTOs.
- **Modern Java:** Use `Stream API`, `Optional`, and `Switch Expressions`.
- **Naming:** `PascalCase` (Class), `camelCase` (Method/Var), `UPPER_SNAKE` (Const).

---

## ⚠️ 4. ERROR HANDLING STANDARDS
- **Centralized Control:** Mandatory use of `@RestControllerAdvice` for global exception management.
- **Exception Hierarchy:** Implement a `BaseException` (extends `RuntimeException`) with specialized subclasses (e.g., `EntityNotFoundException`, `UnauthorizedException`).
- **Validation Handling:** Intercept `MethodArgumentNotValidException` and return field-specific error details within the `errors` object.
- **Security & Privacy:** Stack traces must be suppressed in the `ApiResponse`. Return only sanitized, meaningful messages.

---

## 📦 5. API RESPONSE FORMAT
```json
{
  "success": true,
  "code": 200,
  "message": "Detailed message",
  "errors": {},
  "data": {},
  "timestamp": 1712930000
}