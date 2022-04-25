package jamoliddin.tj.mytest.presentation.screens.auth.register

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import jamoliddin.tj.mytest.R
import jamoliddin.tj.mytest.data.model.User
import jamoliddin.tj.mytest.domain.model.Screen
import jamoliddin.tj.mytest.presentation.components.*
import jamoliddin.tj.mytest.presentation.components.PrimaryButton
import jamoliddin.tj.mytest.presentation.screens.auth.AuthState
import jamoliddin.tj.mytest.presentation.screens.auth.AuthViewModel
import jamoliddin.tj.mytest.presentation.theme.Primary

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterScreenPersonalData(
    navController: NavController,
    userEmail: String? = null,
    authViewModel: AuthViewModel = viewModel()
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val saveState = authViewModel.saveUserState

    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var napravlenie by remember {
        mutableStateOf("")
    }
    var course by remember {
        mutableStateOf(1)
    }
    var univer by remember {
        mutableStateOf("")
    }

    val state = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 64.dp, top = 40.dp, end = 64.dp)
            .verticalScroll(state),
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
            onTextChanged = { firstName = it },
            placeHolderText = "Фамилия",
            maxCount = 99,
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            )
        )

        Spacer(modifier = Modifier.padding(8.dp))

        CustomTextField(
            onTextChanged = { lastName = it },
            placeHolderText = "Имя",
            maxCount = 99,
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            )
        )

        Spacer(modifier = Modifier.padding(8.dp))

        CustomTextField(
            onTextChanged = { napravlenie = it },
            placeHolderText = "Направление",
            maxCount = 99,
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            )
        )

        Spacer(modifier = Modifier.padding(8.dp))

        CourseSelection(choose = { course = it })

        Spacer(modifier = Modifier.padding(8.dp))

        CustomTextField(
            onTextChanged = { univer = it },
            placeHolderText = "Университет",
            maxCount = 99,
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            )
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Spacer(modifier = Modifier.padding(32.dp))

        PrimaryButton(
            stringId = R.string.createUser
        ) {
           if (firstName != "" && lastName != "" && napravlenie != "" && univer != ""){
               val user = User(
                   firstName = firstName,
                   lastName = lastName,
                   napravlenie = napravlenie,
                   course = course,
                   univer = univer,
                   email = userEmail
               )
               Log.d("TAG", "RegisterScreenPersonalData: User-> $user")
               authViewModel.saveUserData(user,context)
           } else Toast.makeText(context, "Заполните все поля для продолжения", Toast.LENGTH_SHORT).show()
        }

        Spacer(modifier = Modifier.padding(100.dp))
    }

    if (saveState.value is AuthState.Loading) {
        Progressbar()
    }

    if (saveState.value is AuthState.Success) {
        Toast.makeText(context, "Вы успешно зарегистрировались", Toast.LENGTH_SHORT).show()
        LaunchedEffect(key1 = saveState.value){
            navController.popBackStack()
            navController.navigate(Screen.MainScreen.route)
        }
    }

    if (saveState.value is AuthState.AuthError) {
        Toast.makeText(
            context,
            (saveState.value as AuthState.AuthError).message,
            Toast.LENGTH_SHORT
        ).show()
    }

}