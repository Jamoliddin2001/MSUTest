package jamoliddin.tj.mytest.presentation.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import jamoliddin.tj.mytest.R
import jamoliddin.tj.mytest.presentation.components.BackButton
import jamoliddin.tj.mytest.presentation.components.CustomTextField
import jamoliddin.tj.mytest.presentation.components.PrimaryButton

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    navController: NavController
) {
    val focusRequester = FocusRequester()
    val keyboardController = LocalSoftwareKeyboardController.current
    var phone by remember {
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
            onTextChanged = {phone = it},
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

        }

    }

}