package jamoliddin.tj.mytest_teachers.presentation.screens.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import jamoliddin.tj.mytest_teachers.presentation.screens.profile.ProfileMenuItems
import jamoliddin.tj.mytest_teachers.presentation.theme.GrayLabel

@Composable
fun BottomSheetContent(
    navController: NavController,
    onItemClicked: (Boolean) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(
            vertical = 16.dp
        )
    ) {
        Box(
            modifier = Modifier
                .size(width = 40.dp, height = 4.dp)
                .background(color = GrayLabel, shape = RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.size(10.dp))
        ProfileMenuItems(navController = navController, onItemCliked = onItemClicked)
        Spacer(modifier = Modifier.padding(bottom = 60.dp))
    }
}