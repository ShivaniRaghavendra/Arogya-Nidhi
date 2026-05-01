# Arogya Nidhi - Healthcare Eligibility Checker

Arogya Nidhi is a comprehensive Android application designed to help users check their eligibility for various government healthcare schemes in India.

## Features

- **Authentication**: Secure email and password login using Firebase Authentication.
- **User Profile**: Store and manage user profiles (name, age, income, district, etc.) in Firebase Firestore.
- **Onboarding**: Informative screens to guide new users through the app's purpose.
- **Eligibility Checker**: A multi-step form that uses decision tree logic to determine eligible schemes based on user input.
- **Schemes Module**: Detailed list of government schemes with information on benefits and application processes.
- **Document Guide**: Track required documents for each scheme, saved locally for offline access using Room.
- **Hospital Finder**: Find nearby government and private hospitals filtered by district.
- **Modern UI/UX**: Built with Jetpack Compose and Material 3, supporting light and dark modes.

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM with Repository Pattern
- **Dependency Injection**: Hilt
- **Backend**: Firebase (Auth, Firestore, Storage)
- **Local Database**: Room (for document tracking)
- **Preferences**: Jetpack DataStore
- **Navigation**: Compose Navigation (Type-Safe)

## Setup Instructions

1. **Clone the repository**:
   ```bash
   git clone https://github.com/example/arogyanidhi.git
   ```
2. **Firebase Setup**:
   - Create a new project on the [Firebase Console](https://console.firebase.google.com/).
   - Add an Android app with package name `com.example.arogyanidhi`.
   - Download the `google-services.json` file and place it in the `app/` directory.
   - Enable **Email/Password** authentication in the Firebase Console.
   - Enable **Firestore Database** and **Firebase Storage**.
3. **Build and Run**:
   - Open the project in Android Studio (Ladybug or newer).
   - Sync Gradle files.
   - Run the app on an emulator or physical device.

## Project Structure

- `data/`: Contains repository implementations, local database (Room), and preference management.
- `domain/`: Contains domain models and repository interfaces.
- `ui/`: Contains Compose screens, ViewModels, and navigation logic.
- `di/`: Hilt modules for dependency injection.

## License

This project is licensed under the MIT License.
