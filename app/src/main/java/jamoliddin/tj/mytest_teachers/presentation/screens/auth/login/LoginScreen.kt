package jamoliddin.tj.mytest_teachers.presentation.screens.auth

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
import androidx.compose.ui.focus.FocusRequester
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
import jamoliddin.tj.mytest_teachers.presentation.components.BackButton
import jamoliddin.tj.mytest_teachers.presentation.components.CustomTextField
import jamoliddin.tj.mytest_teachers.presentation.components.PrimaryButton
import jamoliddin.tj.mytest_teachers.presentation.components.Progressbar

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel()
) {

    val loginState = authViewModel.loginState
    val context = LocalContext.current


    val focusRequester = FocusRequester()
    val keyboardController = LocalSoftwareKeyboardController.current
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
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

        Spacer(modifier = Modifier.padding(16.dp))

        CustomTextField(
            onTextChanged = {email = it},
            focusRequester = focusRequester,
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
            onTextChanged = {password = it},
            focusRequester = focusRequester,
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

        Spacer(modifier = Modifier.padding(32.dp))

        PrimaryButton(stringId = R.string.login) {
            if(email.isNotEmpty() && password.isNotEmpty()){
                authViewModel.login(email.trim(), password,context)
            } else Toast.makeText(context, "Заполните поля", Toast.LENGTH_SHORT).show()
        }

    }

    if(loginState.value is AuthState.Loading){
        Progressbar()
    }

    if(loginState.value is AuthState.Success){
        LaunchedEffect(key1 = loginState.value ){
            navController.navigate(Screen.MainScreen.route){
                popUpTo(0){
                    inclusive = true
                }
            }
        }
    }

    if(loginState.value is AuthState.AuthError){
        Toast.makeText(context, (loginState.value as AuthState.AuthError).message, Toast.LENGTH_SHORT).show()
    }

}