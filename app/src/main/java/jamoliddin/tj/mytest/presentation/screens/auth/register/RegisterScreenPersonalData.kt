package jamoliddin.tj.mytest.presentation.screens.auth.register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import jamoliddin.tj.mytest.presentation.components.BackButton
import jamoliddin.tj.mytest.presentation.theme.Primary

@Composable
fun RegisterScreenPersonalData(
    navController: NavController
) {

    BackButton(navController = navController)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text("PersonalData", color = Primary)
    }
}