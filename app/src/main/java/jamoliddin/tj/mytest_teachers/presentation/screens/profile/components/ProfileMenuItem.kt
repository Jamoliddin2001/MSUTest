package jamoliddin.tj.mytest_teachers.presentation.screens.profile.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import jamoliddin.tj.mytest_teachers.R
import jamoliddin.tj.mytest_teachers.domain.model.Screen
import jamoliddin.tj.mytest_teachers.presentation.theme.BloodRed
import jamoliddin.tj.mytest_teachers.presentation.theme.Headlines

@Composable
fun ProfileMenuItem(
    item: ProfileMenuItem,
    navController: NavController,
    onClick: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                val isLogout = item is ProfileMenuItem.Logout
                onClick(isLogout)
                if (isLogout) return@clickable
                navController.navigate(item.screen.route)
            }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = item.painterRes),
            tint = Color.Unspecified,
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = item.label,
            color = if(item is ProfileMenuItem.Logout) BloodRed else Headlines
        )
    }
}


sealed class ProfileMenuItem(
    @DrawableRes val painterRes: Int,
    val screen: Screen,
    val label: String
) {

    companion object {
        fun getAll() = listOf(
            PersonalData, InfoScreen, Logout
        )
    }

    object PersonalData : ProfileMenuItem(
        label = "Личная информация",
        painterRes = R.drawable.ic_user_circle,
        screen = Screen.PersonalDataScreen
    )

    object InfoScreen : ProfileMenuItem(
        label = "Информация",
        painterRes = R.drawable.ic_info,
        screen = Screen.InfoScreen
    )

    object Logout : ProfileMenuItem(
        label = "Выйти",
        painterRes = R.drawable.ic_exit,
        screen = Screen.SplashScreen
    )

}