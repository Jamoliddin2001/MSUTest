package jamoliddin.tj.mytest.presentation.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import jamoliddin.tj.mytest.presentation.components.BackButton

@Composable
fun LoginScreen(
    navController: NavController
) {
    val controller = rememberSystemUiController()

    LaunchedEffect(key1 = controller) {
        controller.setSystemBarsColor(
            color = Color.White
        )
    }

    BackButton(navController = navController)

}