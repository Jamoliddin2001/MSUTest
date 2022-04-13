package jamoliddin.tj.mytest.presentation.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import jamoliddin.tj.mytest.R
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

    Column(
        modifier = Modifier
            .offset(y = ((-48).dp))
            .padding(horizontal = 64.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            modifier = Modifier
                .size(140.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.msu),
            contentDescription = "Logo"
        )

        Spacer(modifier = Modifier.padding(16.dp))

        Text(
            text = "Вход",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

    }

}