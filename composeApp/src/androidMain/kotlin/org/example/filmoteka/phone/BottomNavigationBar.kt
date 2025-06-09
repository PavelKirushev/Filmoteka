package org.example.filmoteka.phone

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.auth.domain.AuthManager
import org.example.filmoteka.models.NavBarItems

@Composable
fun BottomNavigationBar(controller: NavController, authManager: AuthManager) {
    val isLoggedIn by authManager.isLoggedInFlow.collectAsState()

    if (!isLoggedIn) return
    NavigationBar(modifier = Modifier.height(70.dp),
        containerColor = MaterialTheme.colorScheme.primaryContainer
    ) {
        val backStackEntry by controller.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.BarItems.forEach { navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    try {
                        controller.navigate(navItem.route) {
                            popUpTo(controller.graph.findStartDestination().id) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    } catch (e: Exception) {

                    }

                },
                icon = {
                    Icon(imageVector = navItem.image,
                        contentDescription = navItem.title, )
                },
                label = {
                    Text(text = navItem.title,)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primaryContainer,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    disabledIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    disabledTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    indicatorColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    }
}