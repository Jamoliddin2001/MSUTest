package jamoliddin.tj.mytest_teachers.presentation.screens.auth.register

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import jamoliddin.tj.mytest_teachers.R
import jamoliddin.tj.mytest_teachers.domain.model.Screen
import jamoliddin.tj.mytest_teachers.presentation.components.*
import jamoliddin.tj.mytest_teachers.presentation.components.BackButton
import jamoliddin.tj.mytest_teachers.presentation.components.PrimaryButton
import jamoliddin.tj.mytest_teachers.presentation.screens.auth.AuthState
import jamoliddin.tj.mytest_teachers.presentation.screens.auth.AuthViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel()
) {
    val authState = authViewModel.authState
    val context = LocalContext.current

    val keyboardController = LocalSoftwareKeyboardController.current
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }

    val controller = rememberSystemUiController()

    LaunchedEffect(key1 = controller) {
        controller.setSystemBarsColor(
            color = Color.White
        )
    }

    BackButton(navController = navController)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .offset(y = (-48).dp)
            .padding(horizontal = 64.dp),
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
            text = "Регистрация",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.padding(16.dp))

        CustomTextField(
            onTextChanged = { email = it },
            placeHolderText = "Электронная почта",
            maxCount = 99,
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            )
        )

        Spacer(modifier = Modifier.padding(8.dp))

        CustomTextField(
            onTextChanged = { password = it },
            isPasswordField = true,
            placeHolderText = "Пароль",
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            )
        )

        Spacer(modifier = Modifier.padding(8.dp))

        CustomTextField(
            onTextChanged = { confirmPassword = it },
            placeHolderText = "Повторите пароль",
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            )
        )

        Spacer(modifier = Modifier.padding(32.dp))

        PrimaryButton() {
            if (email != "" && password != "" && confirmPassword != "") {
                if (password != confirmPassword) {
                    Toast.makeText(context, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                } else authViewModel.register(email, password)
            } else Toast.makeText(context, "Заполните поля", Toast.LENGTH_SHORT).show()
        }
    }
    if (authState.value is AuthState.Loading) {
        Progressbar()
    }

    if (authState.value is AuthState.Success) {
        LaunchedEffect(key1 = authState.value){
            navController.navigate(Screen.RegisterScreenPersonalData.route+"/$email"){
                popUpTo(0){
                    inclusive = true
                }
            }
        }
    }

    if (authState.value is AuthState.AuthError) {
        Toast.makeText(
            context,
            (authState.value as AuthState.AuthError).message,
            Toast.LENGTH_SHORT
        ).show()
    }
}