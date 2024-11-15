# College Management App 🎓

A **College Management** app designed for students and administrators, built using **Jetpack Compose**, **Kotlin**, **Room**, and **Retrofit**. The app enables students to manage their academic information while providing admins with the ability to oversee and update college data efficiently.

## Features ✨

### Student App
- **View Courses**: Students can view the list of available courses.
- **Class Schedule**: Access a timetable of classes.
- **Assignments & Exams**: Keep track of assignments and upcoming exams.
- **Profile Management**: Students can manage their personal and academic details.
- **Notifications**: Receive important announcements and updates.

### Admin App
- **Manage Banner**: Admins can add, edit, and remove College Banner fot User.
- **Manage Faculty**: Add and modify profiles of Teachers or Faculty.
- **Data Synchronization**: Data is updated in real-time using Firebse for network calls and Room for local persistence.

## Tech Stack 🛠️

- **Kotlin** - Primary programming language for Android development.
- **Jetpack Compose** - Modern UI toolkit for building native Android user interfaces.
  **Firebase** - Add data to Firebase 
- **ViewModel** - Lifecycle-aware component for UI data management.
- **Coroutines** - For handling asynchronous tasks like API calls and database operations.



## Getting Started 🚀

Follow these steps to set up and run the project locally.

### Prerequisites

- Android Studio (latest version)
- Kotlin (configured in Android Studio)
- Gradle
- Backend server or API (if using Retrofit for remote data management)

### Installation

1. Clone the repository:
  https://github.com/pushkartiwarii/CollegeApp.git
    ```

2. Open the project in Android Studio.

3. Sync the project with Gradle.

4. Set up the required backend services for the admin panel (if applicable).

5. Run the app on an emulator or a physical device.

### Usage

- **Student App**:
  - View Notices and schedules, manage profiles, and keep track of Faculty.
- **Admin App**:
  - Manage student and course data, send notifications, and generate reports.

## Architecture 🏛️

This project follows a **MVVM (Model-View-ViewModel)** architecture:
- **Model**: Handles data from the local Room database and remote API (via Retrofit).
- **ViewModel**: Provides data to the UI and handles business logic.
- **View**: Built using Jetpack Compose, which reacts to changes in the ViewModel.
