package jamoliddin.tj.mytest.presentation.screens.welcome

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import jamoliddin.tj.mytest.R
import jamoliddin.tj.mytest.data.local.SharedPreferences
import jamoliddin.tj.mytest.domain.model.Screen
import jamoliddin.tj.mytest.presentation.components.SplashBackdrop
import jamoliddin.tj.mytest.presentation.theme.Primary
import jamoliddin.tj.mytest.presentation.theme.myDark
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    val context = LocalContext.current
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        )
    )

    LaunchedEffect(key1 = true ){
        startAnimation = true
        delay(3500)

        val sharedPreferences = SharedPreferences(context)
        val route =  if(sharedPreferences.isAuthorized()) Screen.MainScreen.route
        else Screen.WelcomeScreen.route

        navController.navigate(route){
            popUpTo(Screen.SplashScreen.route){
                inclusive = true
            }
        }
    }
    val controller = rememberSystemUiController()

    SideEffect {
        controller.setSystemBarsColor(
            color = Color.White
        )
    }

    SplashBackdrop(alphaAnim.value)
}