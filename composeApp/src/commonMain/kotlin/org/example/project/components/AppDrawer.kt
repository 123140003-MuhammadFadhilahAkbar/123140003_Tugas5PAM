package org.example.project.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class DrawerItem(
    val icon  : ImageVector,
    val label : String,
    val route : String
)

@Composable
fun AppDrawerContent(
    currentRoute  : String?,
    onNavigate    : (String) -> Unit,
    onCloseDrawer : () -> Unit
) {
    val drawerItems = listOf(
        DrawerItem(Icons.Default.List,     "Catatan Saya", "note_list"),
        DrawerItem(Icons.Default.Favorite, "Favorit",      "favorites"),
        DrawerItem(Icons.Default.Person,   "Profil",       "profile"),
    )

    ModalDrawerSheet(
        drawerShape = RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF1A73E8), Color(0xFF6C63FF))
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier         = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("📝", fontSize = 30.sp)
                }
                Spacer(Modifier.height(10.dp))
                Text(
                    text       = "Notes App",
                    color      = Color.White,
                    fontSize   = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text     = "Tugas 5 PAM · ITERA",
                    color    = Color.White.copy(alpha = 0.8f),
                    fontSize = 12.sp
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        Text(
            text       = "MENU",
            fontSize   = 11.sp,
            fontWeight = FontWeight.SemiBold,
            color      = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier   = Modifier.padding(horizontal = 20.dp, vertical = 4.dp)
        )

        drawerItems.forEach { item ->
            val selected = currentRoute == item.route
            NavigationDrawerItem(
                icon   = {
                    Icon(
                        imageVector        = item.icon,
                        contentDescription = item.label,
                        tint               = if (selected) MaterialTheme.colorScheme.primary
                                             else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                label  = {
                    Text(
                        text       = item.label,
                        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
                    )
                },
                selected = selected,
                onClick  = {
                    onNavigate(item.route)
                    onCloseDrawer()
                },
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp),
                colors   = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor   = MaterialTheme.colorScheme.primaryContainer,
                    selectedTextColor        = MaterialTheme.colorScheme.primary,
                    selectedIconColor        = MaterialTheme.colorScheme.primary,
                    unselectedContainerColor = Color.Transparent
                )
            )
        }

        Spacer(Modifier.weight(1f))

        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

        Row(
            modifier          = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector        = Icons.Default.Info,
                contentDescription = null,
                tint               = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier           = Modifier.size(16.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text     = "v1.0.0 · IF25-22017 · 2025",
                fontSize = 11.sp,
                color    = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
