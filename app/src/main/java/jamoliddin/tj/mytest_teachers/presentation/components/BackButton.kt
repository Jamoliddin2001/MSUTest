package jamoliddin.tj.mytest_teachers.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import jamoliddin.tj.mytest_teachers.presentation.theme.GrayLabel

@Composable
internal fun BackButton(
    modifier: Modifier = Modifier,
    navController: NavController,
    icon: ImageVector = Icons.Default.ArrowBackIosNew,
    tint: Color = GrayLabel
) {
    IconButton(
        modifier = modifier.padding(all = 8.dp),
        onClick = { navController.navigateUp() }
    ) {
        Icon(
            imageVector = icon,
            tint = tint,
            contentDescription = null
        )
    }
}