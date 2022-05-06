package jamoliddin.tj.mytest_teachers.presentation.screens.welcome

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import jamoliddin.tj.mytest_teachers.R
import jamoliddin.tj.mytest_teachers.domain.model.Screen
import jamoliddin.tj.mytest_teachers.presentation.theme.Primary

@Composable
fun WelcomeScreen(navController: NavController) {

    val controller = rememberSystemUiController()
    
    LaunchedEffect(key1 = controller){
        controller.setStatusBarColor(
            color = Primary
        )
    }

    Surface(color = Primary) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.welcome_screen_background),
                contentDescription = null
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = (-16).dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                 Text(
                    modifier = Modifier
                        .offset(y = (-16).dp),
                    text = "TEACHERS",
                    color = Color.White,
                    fontWeight = FontWeight.Black,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )

                Button(
                    onClick = {
                        navController.navigate(Screen.LoginScreen.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 64.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(size = 12.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = Primary
                    )
                ) {
                    Text(
                        text = "Войти",
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.padding(16.dp))

                OutlinedButton(
                    onClick = {
                        navController.navigate(Screen.RegisterScreen.route)
                    },
                    shape = RoundedCornerShape(size = 12.dp),
                    border = BorderStroke(width = 1.dp, color = Color.White),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 64.dp)
                        .height(48.dp),
                ) {
                    Text(
                        text = "Зарегистрироваться",
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

    }


}