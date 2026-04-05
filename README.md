# 📝 Notes App — Tugas 5 PAM

**Nama:** Muhammad Fadhilah Akbar  
**NIM:** 123140003    
**Mata Kuliah:** IF25-22017 Pengembangan Aplikasi Mobile — ITERA  

---

## 📋 Deskripsi

Notes App adalah aplikasi manajemen catatan berbasis **Kotlin Multiplatform (KMP)** dengan **Jetpack Compose**. Tugas 5 berfokus pada implementasi sistem navigasi menggunakan **Navigation Component** dengan fitur Bottom Navigation, argument passing, dan Navigation Drawer sebagai bonus.

---

## 🗂️ Struktur Folder

```
composeApp/src/commonMain/kotlin/org/example/project/
├── App.kt                          # Entry point
├── model/
│   └── Note.kt                     # Data class Note & NoteColor enum
├── navigation/
│   ├── Screen.kt                   # Sealed class semua routes (type-safe)
│   └── AppNavigation.kt            # NavHost, BottomNav, Drawer, ColorScheme
├── screens/
│   ├── NoteListScreen.kt           # Tab 1 — daftar catatan + FAB
│   ├── FavoritesScreen.kt          # Tab 2 — catatan favorit
│   ├── ProfileScreenWrapper.kt     # Tab 3 — profil pengguna
│   ├── NoteDetailScreen.kt         # Detail catatan (noteId argument)
│   └── NoteFormScreens.kt          # Add & Edit catatan (shared form)
├── viewmodel/
│   ├── NoteViewModel.kt            # State management catatan
│   └── ProfileViewModel.kt         # State management profil + dark mode
└── components/
    └── AppDrawer.kt                # BONUS: Navigation Drawer content
```

---

## ✅ Fitur yang Diimplementasikan

### 1. Bottom Navigation (25%)
- 3 tab: **Catatan**, **Favorit**, **Profil**
- State tab tersimpan saat pindah (`saveState = true`, `restoreState = true`)
- Tab aktif ditandai dengan icon filled + warna primary
- Bottom bar otomatis **tersembunyi** di detail/form screens
- Implementasi di: `AppNavigation.kt` → `AppBottomNavBar()`

### 2. Navigation with Arguments (25%)
- `noteId: Int` di-pass sebagai `NavType.IntType`
- Route dengan argument: `note_detail/{noteId}`, `edit_note/{noteId}`
- Helper `createRoute(noteId)` di sealed class `Screen` untuk type-safety
- `noteId` ditampilkan di NoteDetailScreen (badge **ID: #X**)
- Implementasi di: `Screen.kt`, `AppNavigation.kt`, `NoteDetailScreen.kt`, `NoteFormScreens.kt`

### 3. Navigation Flow (20%)
| Flow | Implementasi |
|------|-------------|
| List → Detail | `onNoteClick { id → navigate(NoteDetail(id)) }` |
| Detail → Edit | `onEdit { id → navigate(EditNote(id)) }` |
| List → Add | FAB `onAddClick { navigate(AddNote) }` |
| Add/Edit → Back | `popBackStack()` |
| Edit → Save | `popBackStack(NoteList, inclusive=false)` |
| Back dari semua screen | `navController.popBackStack()` |

### 4. Code Structure (20%)
- Routes terpusat di `sealed class Screen` — tidak ada magic string
- ViewModel tidak memegang NavController (MVVM clean)
- State hoisting: callback lambda dari UI ke ViewModel
- Folder terpisah: `navigation/`, `screens/`, `components/`, `viewmodel/`, `model/`

### 5. Dokumentasi (10%)
- README ini beserta flow diagram
- Screenshot setiap screen (lihat bagian Screenshots)

### 🎁 BONUS: Navigation Drawer (+10%)
- `ModalNavigationDrawer` membungkus seluruh UI
- Dibuka via tombol **hamburger (≡)** di TopAppBar NoteListScreen
- Gesture swipe dari kiri juga berfungsi (hanya di tab utama)
- Berisi link ke semua 3 tab + header bergradient + info versi
- Implementasi di: `components/AppDrawer.kt`, `AppNavigation.kt`

---

## 🗺️ Navigation Flow Diagram

<img width="1438" height="736" alt="Image" src="https://github.com/user-attachments/assets/4883b7a9-3bb9-4550-86c8-16497684a5b9" />

---

## 📱 Screenshots

> **Catatan:** Ganti bagian ini dengan screenshot aktual dari emulator/device setelah menjalankan aplikasi.

| Screen | Deskripsi |
|--------|-----------|
| <details><summary><code>screenshot_note_list.png</code></summary><br><img width="300" height="1280" alt="Image" src="https://github.com/user-attachments/assets/4ffe682a-57b8-4749-b7a1-1d2ff79756b8" /></details> | Tab Catatan - daftar note dengan FAB dan hamburger menu |
| <details><summary><code>screenshot_favorites.png</code></summary><br><img width="300" height="1280" alt="Image" src="https://github.com/user-attachments/assets/185a0b2f-c884-4b72-990b-40a0af9e6afc" /></details> | Tab Favorit - note yang ditandai favorit |
| <details><summary><code>screenshot_profile.png</code></summary><br><img width="300" height="1280" alt="Image" src="https://github.com/user-attachments/assets/6a729d8a-fb96-4a01-9b45-8550ceb311fa" /></details> | Tab Profil - profil pengguna dengan dark mode toggle |
| <details><summary><code>screenshot_note_detail.png</code></summary><br><img width="300" height="1280" alt="Image" src="https://github.com/user-attachments/assets/d1a3fea7-726f-4abb-ae7c-69e107285046" /></details> |  Detail catatan - menampilkan noteId, judul, isi |
| <details><summary><code>screenshot_add_note.png</code></summary><br><img width="300" height="1280" alt="Image" src="https://github.com/user-attachments/assets/52c06dc8-9f28-4701-9754-bf35eb74341c" /></details> |   Form tambah catatan - color picker + input |
| <details><summary><code>screenshot_edit_note.png</code></summary><br><img width="300" height="1280" alt="Image" src="https://github.com/user-attachments/assets/9c18ff5c-48a1-4b66-83aa-c50826bf3e89" /></details> |   Form edit catatan - prefill dari data existing |
| <details><summary><code>screenshot_drawer.png</code></summary><br><img width="300" height="1280" alt="Image" src="https://github.com/user-attachments/assets/ad606617-dcf1-4005-8ada-a0e706eeab49" /></details> |   Navigation Drawer - menu slide dari kiri (BONUS) |
| <details><summary><code>screenshot_dark_mode.png</code></summary><br><img width="300" height="1280" alt="Image" src="https://github.com/user-attachments/assets/70abe9e4-f6e2-4c17-b86d-cfdf9f870df4" /></details> |   Dark mode aktif - semua screen dalam tema gelap |

---

## 🎬 Video Demo

> File: `demo_week5.mp4` (≤ 30 detik)

Urutan demo yang disarankan:
1. Buka app → tampil NoteListScreen
2. Buka Navigation Drawer (swipe/hamburger)
3. Tap catatan → NoteDetailScreen (lihat ID argument)
4. Tap Edit → EditNoteScreen → Simpan → kembali ke list
5. Tap FAB → AddNoteScreen → isi form → Simpan
6. Tap tab Favorit → FavoritesScreen
7. Tap tab Profil → toggle Dark Mode

---

## 🛠️ Tech Stack

| Komponen | Teknologi |
|----------|-----------|
| Language | Kotlin Multiplatform |
| UI | Jetpack Compose / Compose Multiplatform |
| Navigation | androidx.navigation.compose |
| State | `mutableStateOf`, `StateFlow`, `collectAsState` |
| Architecture | MVVM + State Hoisting |
| Build | Gradle (JDK 17) |

---

## 📦 Dependencies (build.gradle.kts)

```kotlin
// Navigation
implementation("androidx.navigation:navigation-compose:2.7.x")

// Compose Material 3
implementation("androidx.compose.material3:material3")

// Material Icons Extended (untuk ikon tambahan)
implementation("androidx.compose.material:material-icons-extended")

// Lifecycle / Coroutines
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
```

---

*© 2025 · IF25-22017 · Institut Teknologi Sumatera*
