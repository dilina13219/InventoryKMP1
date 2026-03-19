📦 InventoryKMP - Multiplatform Management System 🚀 Project Overview This project is a cross-platform Inventory Management application built using the Kotlin Multiplatform (KMP) ecosystem. It demonstrates shared business logic and UI components across Android, iOS, and Web platforms, ensuring a consistent user experience with minimal code duplication.

🛠️ Tech Stack Language: Kotlin

UI Framework: Compose Multiplatform (Shared UI)

Database: SQLDelight for local persistence (SQLite)

Architecture: Clean Architecture with a dedicated Repository pattern

Build Tool: Gradle Kotlin DSL (fixed library versions)

✨ Features CRUD Operations: Create, Read, Update, and Delete inventory items.

Category Management: Organize items by categories.

Live Search: Real-time filtering of items by name.

Centralized Theming: Unified color and typography system across all targets.

📁 Project Structure composeApp/commonMain: Contains the shared UI (Compose) and business logic.

shared/sqldelight: Database schema definitions (.sq files).

composeApp/androidMain: Android-specific implementations and Driver Factory.

composeApp/iosMain: iOS-specific SqlDriver configurations.

🛠️ Setup & Installation Clone the repository: git clone [Your-Repo-Link]

Open in Android Studio (Ladybug or later).

Sync Gradle and ensure all fixed dependencies are resolved.

Run the application:

Android: Select composeApp and run.

Web: Run ./gradlew :composeApp:wasmJsBrowserRun

📝 Development Journey (Issue Log) Significant technical hurdles, including SQLDelight code generation issues and KMP-specific UI state management, have been documented in the Issue Log PDF included in this repository.
