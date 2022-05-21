package jamoliddin.tj.mytest_teachers.presentation.screens.main

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import jamoliddin.tj.mytest_teachers.R
import jamoliddin.tj.mytest_teachers.data.model.my_questions.MyQuestions
import jamoliddin.tj.mytest_teachers.domain.UiState
import jamoliddin.tj.mytest_teachers.domain.model.Screen
import jamoliddin.tj.mytest_teachers.domain.util.TAG
import jamoliddin.tj.mytest_teachers.presentation.components.*
import jamoliddin.tj.mytest_teachers.presentation.components.BackButton
import jamoliddin.tj.mytest_teachers.presentation.components.PrimaryButton
import jamoliddin.tj.mytest_teachers.presentation.theme.GrayIndicator
import jamoliddin.tj.mytest_teachers.presentation.theme.Primary

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ShowMyTestScreen(
    navController: NavController,
    subject: String? = "",
    showMyTestViewModel: ShowMyTestViewModel = viewModel(),
    addMyTestViewModel: AddMyTestViewModel = viewModel()
) {

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = true){
        if (subject != null) {
            showMyTestViewModel.getTest(subject)
        }
    }
    val stateTest = showMyTestViewModel.stateShowTest.value

    if(stateTest is UiState.Loading){
        Progressbar()
    }

    if(stateTest is UiState.Success){
        Log.d(TAG, "ShowMyTestScreen: ${stateTest.data}")
        var subjects by remember {
            mutableStateOf(stateTest.data?.subject)
        }
        var countQuestion by remember {
            mutableStateOf(stateTest.data?.countQuestion.toString())
        }
        var course by remember {
            mutableStateOf(stateTest.data?.course.toString())
        }
        var minRating3 by remember {
            mutableStateOf(stateTest.data?.minRating3.toString())
        }
        var minRating4 by remember {
            mutableStateOf(stateTest.data?.minRating4.toString())
        }
        var minRating5 by remember {
            mutableStateOf(stateTest.data?.minRating5.toString())
        }
        var timeToDo by remember {
            mutableStateOf(stateTest.data?.timeToDo.toString())
        }
        var next by remember {
            mutableStateOf(false)
        }
        var listOfQuestion by remember {
            mutableStateOf(stateTest.data?.questions)
        }
        var varA by remember {
            mutableStateOf("")
        }
        var varB by remember {
            mutableStateOf("")
        }
        var varC by remember {
            mutableStateOf("")
        }
        var varD by remember {
            mutableStateOf("")
        }
        var answer by remember {
            mutableStateOf("")
        }

        if(!next) BackButton(navController = navController)

        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.TopEnd,
        ) {
            IconButton(
                onClick = {
                    val myQuestions = MyQuestions(
                        countQuestion = countQuestion.toInt(),
                        course = course.toInt(),
                        minRating3 = minRating3.toInt(),
                        minRating4 = minRating4.toInt(),
                        minRating5 = minRating5.toInt(),
                        subject = stateTest.data?.subject,
                        questions = stateTest.data!!.questions,
                        teacherID = stateTest.data.teacherID,
                        timeToDo = timeToDo.toInt()
                    )
                    addMyTestViewModel.addMyQuestions(myQuestions, true)
                } ,
                modifier = Modifier.padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = null,
                    tint = Primary
                )
            }
        }

        val state = rememberScrollState()
        if (!next){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
//            .verticalScroll(state),
//        verticalArrangement = Arrangement.Center
            ) {

                Spacer(modifier = Modifier.padding(8.dp))

                Text(
                    text = subjects.toString(),
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.padding(16.dp))


                CustomTextField(
                    onTextChanged = { countQuestion = it },
                    firstText = countQuestion,
                    placeHolderText = "Количество вопросов",
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Phone,
                        capitalization = KeyboardCapitalization.Sentences
                    )
                )

                Spacer(modifier = Modifier.padding(8.dp))

                CustomTextField(
                    onTextChanged = { course = it },
                    firstText = course,
                    placeHolderText = "Курс",
                    maxCount = 1,
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Phone,
                        capitalization = KeyboardCapitalization.Sentences
                    )
                )

                Spacer(modifier = Modifier.padding(8.dp))

                CustomTextField(
                    onTextChanged = { minRating3 = it },
                    firstText = minRating3,
                    placeHolderText = "Минимальная оценка 3",
                    maxCount = 2,
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Phone,
                        capitalization = KeyboardCapitalization.Sentences
                    )
                )

                Spacer(modifier = Modifier.padding(8.dp))

                CustomTextField(
                    onTextChanged = { minRating4 = it },
                    firstText = minRating4,
                    placeHolderText = "Минимальная оценка 4",
                    maxCount = 2,
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Phone,
                        capitalization = KeyboardCapitalization.Sentences
                    )
                )

                Spacer(modifier = Modifier.padding(8.dp))

                CustomTextField(
                    onTextChanged = { minRating5 = it },
                    firstText = minRating5,
                    placeHolderText = "Минимальная оценка 5",
                    maxCount = 3,
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Phone,
                        capitalization = KeyboardCapitalization.Sentences
                    )
                )

                Spacer(modifier = Modifier.padding(8.dp))

                CustomTextField(
                    onTextChanged = { timeToDo = it },
                    firstText = timeToDo,
                    placeHolderText = "Время для теста (в секундах)",
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Phone,
                        capitalization = KeyboardCapitalization.Sentences
                    )
                )

                Spacer(modifier = Modifier.padding(16.dp))

                PrimaryButton(
                    stringId = R.string.questions
                ) {
                    if(subject!!.isNotEmpty() && countQuestion.isNotEmpty() && course.isNotEmpty() && minRating3.isNotEmpty()
                        && minRating4.isNotEmpty() && minRating5.isNotEmpty() && timeToDo.isNotEmpty()){
                        next = true
                    } else Toast.makeText(context, "Заполните все поля для продолжения", Toast.LENGTH_SHORT).show()
                }
            }
        }

        if(next){
            if(listOfQuestion != null){
                Spacer(modifier = Modifier.padding(10.dp))
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(GrayIndicator))
                LazyColumn{
                    items(listOfQuestion!!){ index ->
                        CardQuestion(question = index)
                    }
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Пока пусто  : )", fontSize = 14.sp, color = Primary)
                }
            }
        }
    }

    if(stateTest is UiState.Error){
        Toast.makeText(context, stateTest.message, Toast.LENGTH_SHORT).show()
    }

    val stateAddQuestion = addMyTestViewModel.stateAddQuestions.value

    if(stateAddQuestion is UiState.Loading){
        Progressbar()
    }

    if(stateAddQuestion is UiState.Success){
        LaunchedEffect(key1 = true){
            navController.navigate(Screen.MainScreen.route){
                popUpTo(0){
                    inclusive = true
                }
            }
        }
    }
    if(stateAddQuestion is UiState.Error){
        Toast.makeText(context, stateAddQuestion.message, Toast.LENGTH_SHORT).show()
    }
}