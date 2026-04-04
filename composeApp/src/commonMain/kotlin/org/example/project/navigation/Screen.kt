package org.example.project.navigation

// ================================================================
// Screen.kt — Tugas 5 PAM
// Semua routes didefinisikan di sini sebagai sealed class
// agar type-safe dan terpusat (tidak ada magic string tersebar)
// ================================================================

sealed class Screen(val route: String) {

    // ── Bottom Navigation Tabs ──────────────────────────────────
    object NoteList  : Screen("note_list")
    object Favorites : Screen("favorites")
    object Profile   : Screen("profile")     // ← App.kt minggu lalu ada di sini

    // ── Detail / Form Screens (tidak ada di bottom nav) ─────────
    object NoteDetail : Screen("note_detail/{noteId}") {
        fun createRoute(noteId: Int) = "note_detail/$noteId"
    }

    object AddNote : Screen("add_note")

    object EditNote : Screen("edit_note/{noteId}") {
        fun createRoute(noteId: Int) = "edit_note/$noteId"
    }
}
