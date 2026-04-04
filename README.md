# 📝 Notes App — Tugas 5 PAM

**Nama:** Muhammad Fadhilah Akbar  
**NIM:** 123140003  
**Kelas:** IF25  
**Mata Kuliah:** Pengembangan Aplikasi Mobile — ITERA  

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

```
                    ┌─────────────────────────────────────┐
                    │         AppNavigation (NavHost)      │
                    │                                      │
                    │   ┌──────────────────────────────┐   │
                    │   │    ModalNavigationDrawer     │   │
                    │   │   (BONUS — swipe kiri / ≡)   │   │
                    │   └──────────────────────────────┘   │
                    │                                      │
          ┌─────────▼─────────┐                           │
          │  Bottom Navigation │                           │
          │  [Catatan][Favorit][Profil]                    │
          └─────────┬─────────┘                           │
                    │                                      │
     ┌──────────────┼──────────────┐                      │
     ▼              ▼              ▼                      │
NoteListScreen  FavoritesScreen  ProfileScreen            │
     │              │                                      │
     │(tap note)    │(tap note)                            │
     ▼              ▼                                      │
NoteDetailScreen ◄──┘                                     │
     │                                                     │
     │(tap Edit)                                           │
     ▼                                                     │
EditNoteScreen                                             │
     │(Simpan)                                             │
     └──────────────► NoteListScreen (popBackStack)        │
                                                           │
NoteListScreen                                            │
     │(tap FAB +)                                          │
     ▼                                                     │
AddNoteScreen                                             │
     │(Simpan / Batal)                                     │
     └──────────────► NoteListScreen (popBackStack)        │
                    └─────────────────────────────────────┘
```

---

## 📱 Screenshots

> **Catatan:** Ganti bagian ini dengan screenshot aktual dari emulator/device setelah menjalankan aplikasi.

| Screen | Deskripsi |
|--------|-----------|
| `screenshot_note_list.png` | Tab Catatan — daftar note dengan FAB dan hamburger menu |
| `screenshot_favorites.png` | Tab Favorit — note yang ditandai favorit |
| `screenshot_profile.png` | Tab Profil — profil pengguna dengan dark mode toggle |
| `screenshot_note_detail.png` | Detail catatan — menampilkan noteId, judul, isi |
| `screenshot_add_note.png` | Form tambah catatan — color picker + input |
| `screenshot_edit_note.png` | Form edit catatan — prefill dari data existing |
| `screenshot_drawer.png` | Navigation Drawer — menu slide dari kiri (BONUS) |
| `screenshot_dark_mode.png` | Dark mode aktif — semua screen dalam tema gelap |

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
