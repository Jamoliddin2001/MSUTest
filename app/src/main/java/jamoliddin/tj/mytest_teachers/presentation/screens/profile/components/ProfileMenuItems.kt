package jamoliddin.tj.mytest_teachers.presentation.screens.profile

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import jamoliddin.tj.mytest_teachers.presentation.screens.profile.components.ProfileMenuItem

@Composable
fun ProfileMenuItems(
    navController: NavController,
    onItemCliked: (Boolean) -> Unit
) {
    LazyColumn {
        items(ProfileMenuItem.getAll()) {
            ProfileMenuItem(
                item = it,
                navController = navController,
                onClick = onItemCliked
            )
        }
    }

}