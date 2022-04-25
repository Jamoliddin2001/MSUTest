package jamoliddin.tj.mytest.presentation.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import jamoliddin.tj.mytest.data.local.SharedPreferences
import jamoliddin.tj.mytest.presentation.screens.auth.AuthViewModel

@Composable
fun MainScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel()
) {

    val context = LocalContext.current
    val sharedPreferences = SharedPreferences(context)

    val user = sharedPreferences.getUser()

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = user.toString())
    }
}