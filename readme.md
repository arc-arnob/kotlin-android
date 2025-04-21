## 🗺️ 2‑Week Kotlin + Android Learning Roadmap

This roadmap helps you learn Kotlin and Android development by building an Expense Tracker app over 12 weeks.

| Week | Focus                                 | Learning Goals                                                                          | Project Milestone                                 |
|------|---------------------------------------|-----------------------------------------------------------------------------------------|---------------------------------------------------|
| 1    | **Kotlin Basics**                     | • Variables (`val`/`var`), types<br>• Control flow (`if`, `when`)<br>• Functions, lambdas | Create a Kotlin console app that reads expenses from STDIN and prints totals. |
| 1   | **OOP in Kotlin**                     | • Classes, interfaces, enums<br>• Data classes, inheritance<br>• Extension functions     | Model `Expense` & `Category` as data classes; write unit tests for their logic. |
| 1   | **Android Fundamentals**              | • Project structure<br>• Activities & layouts (XML)<br>• Resources & manifest           | Scaffold a new Android app: show a “Hello, Expense Tracker” screen.            |
| 1    | **Views & RecyclerView**              | • View hierarchy, ViewBinding<br>• RecyclerView + Adapters                              | Display a hard‑coded list of expenses in a scrollable list.                    |
| 1    | **Jetpack Compose (optional)**        | • Composable functions<br>• State & recomposition<br>• Theming                         | Re‑implement your expense list in Compose.                                     |
| 1   | **Data Persistence**                  | • Room database<br>• DAO & Entities<br>• Migrations                                     | Add Room: save, load, delete expenses; connect them to your list.              |
| 2    | **Architecture & DI**                 | • MVVM pattern<br>• ViewModel & LiveData/Flow<br>• Hilt for dependency injection        | Refactor to MVVM; inject your `ExpenseRepository` via Hilt.                    |
| 2    | **Networking & Coroutines**           | • Retrofit + serialization (Moshi/Kotlinx)<br>• Kotlin Coroutines + Flow                | Fetch currency rates from a public API and display converted amounts.          |
| 2    | **Authentication & Cloud Sync**       | • Firebase Auth (email/Google)<br>• Firestore basics<br>• Offline‑first patterns        | Let users sign in & sync their expenses to Firestore.                          |
| 2   | **Background Work & Notifications**   | • WorkManager for periodic sync<br>• Notifications & channels                           | Schedule a daily summary notification of today’s expenses.                     |
| 2   | **Testing (TDD)**                     | • Unit tests with JUnit + MockK<br>• Compose/Espresso UI tests<br>• Test‑driven features | Write tests first for adding/removing expenses; ensure 80% coverage on core modules. |
| 2   | **Polish & Publish**                  | • Theming (light/dark)<br>• Accessibility<br>• Play Store prep (app bundles, signing)    | Finalize UI polish, accessibility labels, generate signed APK, upload to Play Console (internal). |

---

### How to Use This Roadmap

1. **Block weekly time** (e.g. 5–10 hours/week).
2. **Follow each week’s goals**—watch one tutorial + read docs + write code/tests.
3. **Commit early and often**: push to GitHub with meaningful commit messages.
4. **Demo each milestone**: record a 1–2 minute screencast to show off features.
5. **Iterate**: if a concept feels shaky, spend an extra day practicing small exercises.  
