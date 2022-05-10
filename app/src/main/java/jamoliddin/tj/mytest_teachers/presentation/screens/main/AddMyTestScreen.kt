package jamoliddin.tj.mytest_teachers.presentation.screens.main

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import jamoliddin.tj.mytest_teachers.data.model.my_questions.MyQuestions
import jamoliddin.tj.mytest_teachers.data.model.my_questions.Question
import jamoliddin.tj.mytest_teachers.domain.UiState
import jamoliddin.tj.mytest_teachers.domain.model.Screen
import jamoliddin.tj.mytest_teachers.domain.util.TAG
import jamoliddin.tj.mytest_teachers.presentation.components.BackButton
import jamoliddin.tj.mytest_teachers.presentation.components.CustomTextField
import jamoliddin.tj.mytest_teachers.presentation.components.PrimaryButton
import jamoliddin.tj.mytest_teachers.presentation.components.Progressbar
import jamoliddin.tj.mytest_teachers.presentation.theme.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddMyTestScreen(
    navController: NavController,
    mainViewModel: MainViewModel = viewModel(),
    addMyTestViewModel: AddMyTestViewModel = viewModel()
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val teacherID = addMyTestViewModel.uid!!
    var subject by remember {
        mutableStateOf("")
    }
    var countQuestion by remember {
        mutableStateOf("")
    }
    var course by remember {
        mutableStateOf("1")
    }
    var minRating3 by remember {
        mutableStateOf("")
    }
    var minRating4 by remember {
        mutableStateOf("")
    }
    var minRating5 by remember {
        mutableStateOf("")
    }
    var timeToDo by remember {
        mutableStateOf("0")
    }
    var next by remember {
        mutableStateOf(false)
    }
    var question by remember {
        mutableStateOf("")
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
//    val listQuestions = arrayListOf<Question>()

    if(!next) BackButton(navController = navController)

    val state = rememberScrollState()
    if (!next){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp)
//            .verticalScroll(state),
//        verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Мой тест",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.padding(16.dp))

            CustomTextField(
                onTextChanged = { subject = it },
                placeHolderText = "Предмет",
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
                onTextChanged = { countQuestion = it },
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

            PrimaryButton() {
                if(subject.isNotEmpty() && countQuestion.isNotEmpty() && course.isNotEmpty() && minRating3.isNotEmpty()
                    && minRating4.isNotEmpty() && minRating5.isNotEmpty() && timeToDo.isNotEmpty()){
                    next = true
                } else Toast.makeText(context, "Заполните все поля для продолжения", Toast.LENGTH_SHORT).show()
            }
        }
    }


    var questionNumber by remember {
        mutableStateOf(1)
    }
    val stateA = remember {
        mutableStateOf(false)
    }
    val stateB = remember {
        mutableStateOf(false)
    }
    val stateC = remember {
        mutableStateOf(false)
    }
    val stateD = remember {
        mutableStateOf(false)
    }
    val errorText = remember {
        mutableStateOf(false)
    }

    fun check():Boolean{
        return varA.isNotEmpty() && varB.isNotEmpty() && varC.isNotEmpty()
                && varD.isNotEmpty() && question.isNotEmpty()
    }

    if(next){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .verticalScroll(state)
                .selectableGroup()
        ) {
            Text(
                text = "Мои вопросы",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.padding(16.dp))

            /*CustomTextField(
                onTextChanged = { question = it },
                maxCount = 600,
                placeHolderText = "Вопрос $questionNumber",
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Sentences
                )
            )*/

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth() ,
                value = question,
                label = { Text(text = "Вопрос $questionNumber", fontSize = 14.sp, color = Primary) },
                onValueChange = { question = it },
                colors = TextFieldDefaults.textFieldColors(
                    focusedLabelColor = GrayLabel,
                    unfocusedLabelColor = GrayLabel,
                    backgroundColor = Color.Transparent,
                    unfocusedIndicatorColor = GrayIndicator,
                    focusedIndicatorColor = Primary,
                    cursorColor = Primary
                ),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences
                )
            )

            Spacer(modifier = Modifier.padding(16.dp))

            Text(
                text = "Варианты ответа:",
                fontSize = 14.sp,
                color = Primary
            )

            Spacer(modifier = Modifier.padding(8.dp))



            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = stateA.value,
                    onClick = {
                        stateA.value = true
                        stateB.value = false
                        stateC.value = false
                        stateD.value = false
                    },
                    modifier = Modifier.padding(end = 8.dp),
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Primary,
                        unselectedColor = GrayLabel
                    )
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = varA,
                    label = { Text(text = "A", fontSize = 14.sp,color = GrayLabel) },
                    onValueChange = { varA = it },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedLabelColor = GrayLabel,
                        unfocusedLabelColor = GrayLabel,
                        backgroundColor = Color.Transparent,
                        unfocusedIndicatorColor = GrayIndicator,
                        focusedIndicatorColor = Primary,
                        cursorColor = Primary
                    ),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences
                    )
                )
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = stateB.value,
                    onClick = {
                        stateA.value = false
                        stateB.value = true
                        stateC.value = false
                        stateD.value = false
                    },
                    modifier = Modifier.padding(end = 8.dp),
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Primary,
                        unselectedColor = GrayLabel
                    )
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = varB,
                    label = { Text(text = "B", fontSize = 14.sp,color = GrayLabel) },
                    onValueChange = { varB = it },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedLabelColor = GrayLabel,
                        unfocusedLabelColor = GrayLabel,
                        backgroundColor = Color.Transparent,
                        unfocusedIndicatorColor = GrayIndicator,
                        focusedIndicatorColor = Primary,
                        cursorColor = Primary
                    ),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences
                    )
                )
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                RadioButton(
                    selected = stateC.value,
                    onClick = {
                        stateA.value = false
                        stateB.value = false
                        stateC.value = true
                        stateD.value = false
                    },
                    modifier = Modifier.padding(end = 8.dp),
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Primary,
                        unselectedColor = GrayLabel
                    )
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = varC,
                    label = { Text(text = "C", fontSize = 14.sp,color = GrayLabel) },
                    onValueChange = { varC = it },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedLabelColor = GrayLabel,
                        unfocusedLabelColor = GrayLabel,
                        backgroundColor = Color.Transparent,
                        unfocusedIndicatorColor = GrayIndicator,
                        focusedIndicatorColor = Primary,
                        cursorColor = Primary
                    ),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences
                    )
                )
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                RadioButton(
                    selected = stateD.value,
                    onClick = {
                        stateA.value = false
                        stateB.value = false
                        stateC.value = false
                        stateD.value = true
                    },
                    modifier = Modifier.padding(end = 8.dp),
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Primary,
                        unselectedColor = GrayLabel
                    )
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = varD,
                    label = { Text(text = "D", fontSize = 14.sp,color = GrayLabel) },
                    onValueChange = { varD = it },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedLabelColor = GrayLabel,
                        unfocusedLabelColor = GrayLabel,
                        backgroundColor = Color.Transparent,
                        unfocusedIndicatorColor = GrayIndicator,
                        focusedIndicatorColor = Primary,
                        cursorColor = Primary
                    ),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences
                    )
                )
            }

            if(errorText.value) Text(text = "Выберите правильный ответ!", fontSize = 14.sp, color = BloodRed)

            Spacer(modifier = Modifier.padding(16.dp))

            /*OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = answer,
                label = { Text(text = "Правильный ответ (выберите вариант)", fontSize = 14.sp,color = DarkerGreen) },
                onValueChange = { answer = it },
                colors = TextFieldDefaults.textFieldColors(
                    focusedLabelColor = GrayLabel,
                    unfocusedLabelColor = GrayLabel,
                    backgroundColor = Color.Transparent,
                    unfocusedIndicatorColor = GrayIndicator,
                    focusedIndicatorColor = DarkerGreen,
                    cursorColor = DarkerGreen
                )
            )*/

            Button(
                onClick = {
                    if (check()){
                        val rightAnswer = when {
                            stateA.value -> varA
                            stateB.value -> varB
                            stateC.value -> varC
                            stateD.value -> varD
                            else -> null
                        }
                        if (rightAnswer != null){
                            val newQuestion = Question(
                                answer = rightAnswer,
                                varA = varA,
                                varB = varB,
                                varC = varC,
                                varD = varD,
                                question = question
                            )
                            addMyTestViewModel.arrayOfQuestions.add(newQuestion)
                            question = ""
                            varA = ""
                            varB = ""
                            varC = ""
                            varD = ""
                            stateA.value = false
                            stateB.value = false
                            stateC.value = false
                            stateD.value = false
                            questionNumber++
                            errorText.value = false
                            Log.d(TAG, "AddMyTestScreen: ${addMyTestViewModel.arrayOfQuestions}")
                        } else errorText.value = true
                    } else Toast.makeText(context, "Заполните все поля для продолжения!", Toast.LENGTH_SHORT).show()

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(size = 12.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = Primary
                )
            ) {
                Text(
                    text = "+ новый вопрос",
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.padding(12.dp))

            OutlinedButton(
                onClick = {
                    if (check()){
                        val rightAnswer = when {
                            stateA.value -> varA
                            stateB.value -> varB
                            stateC.value -> varC
                            stateD.value -> varD
                            else -> null
                        }
                        if (rightAnswer != null){
                            val newQuestion = Question(
                                answer = rightAnswer,
                                varA = varA,
                                varB = varB,
                                varC = varC,
                                varD = varD,
                                question = question
                            )
                            addMyTestViewModel.arrayOfQuestions.add(newQuestion)
                            errorText.value = false
                            Log.d(TAG, "AddMyTestScreen: ${addMyTestViewModel.arrayOfQuestions}")
                            val myQuestions = MyQuestions(
                                countQuestion = countQuestion.toInt(),
                                course = course.toInt(),
                                minRating3 = minRating3.toInt(),
                                minRating4 = minRating4.toInt(),
                                minRating5 = minRating5.toInt(),
                                subject = subject,
                                questions = addMyTestViewModel.arrayOfQuestions,
                                teacherID = teacherID,
                                timeToDo = timeToDo.toInt()
                            )
                            addMyTestViewModel.addMyQuestions(myQuestions)

                        } else errorText.value = true
                    } else Toast.makeText(context, "Заполните все поля для продолжения!", Toast.LENGTH_SHORT).show()
                },
                shape = RoundedCornerShape(size = 12.dp),
                border = BorderStroke(width = 1.dp, color = Color.White),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Primary,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .height(48.dp),
            ) {
                Text(
                    text = "Сохранить тест",
                    fontWeight = FontWeight.Medium
                )
            }

        }
        
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