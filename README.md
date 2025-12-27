# 📝 TaskFlow: A Persistent Todo App

A sleek and functional Todo application built with **Kotlin** and **Android Studio**. This app focuses on delivering a seamless user experience with real-time UI updates and local data persistence.

---

## 🚀 Features

- **Quick Task Entry**: Add tasks instantly with a single tap.
- **Smart Persistence**: Tasks are saved locally using `SharedPreferences`, ensuring your data is safe even after a reboot.
- **Gesture-Based Deletion**: Long-press any task to remove it from your list.
- **Dynamic Empty State**: The UI automatically toggles a "No tasks" message based on your list's content.
- **Input Validation**: Built-in logic to prevent empty or accidental task entries.

---

## 🛠 Tech Stack

- **Language**: Kotlin 
- **Platform**: Android
- **Persistence**: SharedPreferences (String Serialization)
- **UI Design**: XML (ConstraintLayout, ListView)
- **Minimum SDK**: API 21+

---

## 🧠 Technical Highlights

### Data Persistence Strategy
Unlike standard implementations that use `StringSet` (which loses the order of items), this project uses a **String Serialization** approach. 
- **Saving**: The `ArrayList` is joined into a single string using a pipe delimiter (`|`).
- **Loading**: The string is split back into an array on startup.
This ensures that the order in which you wrote your tasks is exactly how they appear every time you open the app.

### UI Synchronization
The app uses a dedicated `updateEmptyState()` method that acts as a "listener" for data changes. Every time a task is added or deleted, the UI re-evaluates whether to show the "Empty List" illustration.

---

## 📂 Project Structure

```text
com.example.todoapp/
├── MainActivity.kt        # Main Logic & Lifecycle Management
├── models/                # (Future) Data classes
└── res/layout/
    └── activity_main.xml  # UI Definition
