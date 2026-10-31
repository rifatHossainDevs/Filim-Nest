# 🎬 Filim Nest - Movies & Video Streaming App

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android" />
  <img src="https://img.shields.io/badge/Language-Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin" />
  <img src="https://img.shields.io/badge/Architecture-Clean%20Architecture%20%2B%20MVVM-FF6F00?style=for-the-badge" alt="Architecture" />
  <img src="https://img.shields.io/badge/DI-Dagger%20Hilt-00897B?style=for-the-badge" alt="Dagger Hilt" />
  <img src="https://img.shields.io/badge/Player-AndroidX%20Media3%20ExoPlayer-E50914?style=for-the-badge" alt="Media3 ExoPlayer" />
  <img src="https://img.shields.io/badge/Min%20SDK-24-blue?style=for-the-badge" alt="Min SDK" />
</p>

---

## 📌 Overview

**Filim Nest** is an Android OTT video streaming application engineered with **Clean Architecture**, **MVVM**, and modern Android development best practices. The app delivers a high-quality cinema and video streaming experience with dynamic categorized content, high-performance streaming playback, seamless search, and secure authentication.

---

## ✨ Features

- 🎥 **Smooth Video Streaming**: High-performance video streaming powered by **AndroidX Media3 (ExoPlayer)** with support for multiple formats, landscape fullscreen, and responsive playback controls.
- 📂 **Categorized Content Browsing**: Explore movies and shows grouped by categories (Hollywood, Bollywood, Horror, Tamil, and more).
- 🔄 **Paginated Video Feeds**: Fast, responsive pagination for endless browsing without memory overhead.
- 🔍 **Real-Time Movie Search**: Instant search filtering to easily locate titles.
- 🌟 **Dynamic Banner Slider**: Featured titles carousel highlighting popular releases.
- 🔐 **Firebase & Google Authentication**: Seamless sign-in and account management powered by Firebase Auth and Google Credential Manager.
- 📄 **Movie Detail Overview**: In-depth movie overviews with release details, descriptions, and recommended titles.
- 📱 **Responsive UI / Scalable Dimensions**: Pixel-perfect UI across different screen densities and tablets using `SDP` and `SSP`.
- 💰 **Monetization Ready**: Google Mobile Ads (AdMob) integration.

---

## 📸 Screenshots

> *Add your app screenshots inside a `/screenshots` folder or replace the placeholders below.*

| Splash & Intro | Home Feed | Category & Details | Video Player |
| :---: | :---: | :---: | :---: |
| <img src="screenshots/splash.png" width="200" alt="Splash Screen" /> | <img src="screenshots/home.png" width="200" alt="Home Feed" /> | <img src="screenshots/detail.png" width="200" alt="Detail Screen" /> | <img src="screenshots/player.png" width="200" alt="Video Player" /> |

---

## 🏗️ Architecture

The app is built following **Clean Architecture** principles and the **MVVM** pattern to ensure testability, scalability, and maintainability.

```
app/src/main/java/com/ruItSolution/filimnest/
│
├── core/                  # Core utilities, Base classes, Network DI, Common helpers
│   ├── base/              # BaseFragment, BaseViewModel
│   ├── common/            # State holders, Resource wrappers (Loading, Success, Error)
│   ├── di/                # Hilt dependency injection modules (NetworkModule, AppModule)
│   └── utils/             # Constants, extensions, GoogleSignInClient
│
├── data/                  # Data Layer: APIs, DTOs, Repository Implementations
│   ├── dto/               # Data Transfer Objects / API response models
│   ├── remote/            # Retrofit API interface definitions
│   └── repoImpl/          # Concrete repository implementations
│
├── domain/                # Domain Layer: Business Logic (Independent of Android framework)
│   ├── model/             # Domain models (UI-friendly entities)
│   ├── repo/              # Repository contracts/interfaces
│   └── useCase/           # Isolated business use cases (GetCategoryList, GetVideoById, etc.)
│
├── presentation/          # UI Layer: Activities, Fragments, ViewModels, UI Data States
│   ├── home/              # Home screen, sliders, category carousels
│   ├── categoryVideo/     # Category video listing & pagination
│   ├── videoDetail/       # Detailed video summary & recommendations
│   ├── videoPlayer/       # AndroidX Media3 ExoPlayer video player
│   ├── search/            # Instant video search
│   └── signup/            # User authentication & Google sign-in
│
└── adapter/               # RecyclerView adapters & ViewHolders
```

---

## 🛠️ Tech Stack & Libraries

- **Language:** [Kotlin](https://kotlinlang.org/)
- **Minimum SDK:** 24 (Android 7.0 Nougat)
- **Target SDK:** 36 (Android 15+)
- **Dependency Injection:** [Dagger Hilt](https://dagger.dev/hilt/)
- **Networking:** [Retrofit 2](https://square.github.io/retrofit/) & [Gson Converter](https://github.com/google/gson)
- **Asynchronous & Concurrency:** Kotlin Coroutines & Flow / LiveData
- **Video Playback Engine:** [AndroidX Media3 ExoPlayer](https://developer.android.com/media/media3)
- **Authentication:** Firebase Authentication & Google Identity Services (Credential Manager)
- **Navigation:** Android Jetpack Navigation Component (with Safe Args)
- **View Binding:** Android Jetpack ViewBinding
- **Image Loading:** [Coil](https://coil-kt.github.io/coil/) & [Glide](https://github.com/bumptech/glide)
- **Responsiveness:** [SDP](https://github.com/intuit/sdp) (Scalable DP) & [SSP](https://github.com/intuit/ssp) (Scalable SP)
- **Monetization:** Google Mobile Ads SDK (AdMob)

---

## 🚀 Getting Started

### Prerequisites

- Android Studio Meerkat (or newer)
- JDK 11 or higher
- Android SDK with API level 36 installed

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/rifatHossainDevs/Filim-Nest.git
   cd Filim-Nest
   ```

2. **Firebase Setup:**
   - Create a project on the [Firebase Console](https://console.firebase.google.com/).
   - Add an Android app with package name: `com.ruItSolution.filimnest`.
   - Download the `google-services.json` file and place it in the `app/` directory.
   - Enable **Google Sign-In** in the Firebase Authentication section.

3. **Backend API Configuration (Optional):**
   - Base URL is configured in `com.ruItSolution.filimnest.core.utils.Constants`:
     ```kotlin
     const val BASE_URL = "https://your-api-endpoint.com/api/v1/"
     ```

4. **Build and Run:**
   - Open the project in Android Studio.
   - Sync the project with Gradle files.
   - Select your target device / emulator and click **Run** (`Shift + F10`).

---

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📬 Contact & Author

Developed by **Rifat Hossain**
- GitHub: [@rifatHossainDevs](https://github.com/rifatHossainDevs)
- Email: support@filimnest.com

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).
