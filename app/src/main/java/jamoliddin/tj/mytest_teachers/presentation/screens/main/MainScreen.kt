package jamoliddin.tj.mytest_teachers.presentation.screens.main

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import jamoliddin.tj.mytest_teachers.R
import jamoliddin.tj.mytest_teachers.data.local.SharedPreferences
import jamoliddin.tj.mytest_teachers.data.model.my_questions.MyQuestions
import jamoliddin.tj.mytest_teachers.data.model.my_questions.Question
import jamoliddin.tj.mytest_teachers.domain.UiState
import jamoliddin.tj.mytest_teachers.domain.model.Screen
import jamoliddin.tj.mytest_teachers.domain.util.TAG
import jamoliddin.tj.mytest_teachers.presentation.components.CardSubject
import jamoliddin.tj.mytest_teachers.presentation.components.PrimaryButton
import jamoliddin.tj.mytest_teachers.presentation.components.Progressbar
import jamoliddin.tj.mytest_teachers.presentation.screens.auth.AuthViewModel
import jamoliddin.tj.mytest_teachers.presentation.theme.*

@Composable
fun MainScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel(),
    mainViewModel: MainViewModel = viewModel()
) {

    val context = LocalContext.current
    val sharedPreferences = SharedPreferences(context)

    val user = sharedPreferences.getTeacher()
    val uid = FirebaseAuth.getInstance().uid.toString()

    val myQuestions = MyQuestions(
        10, 1, 50, 75, 85, "MATH",
        listOf(
            Question("Is", "A", "B", "C", "D", "1 How are you?"),
            Question("Is", "A", "B", "C", "D", "2 How are you?"),
        ), uid, 180
    )

//    LaunchedEffect(key1 = true){
//        mainViewModel.addMyQuestions(myQuestions)
//    }

    val stateGetAllSubjects = mainViewModel.stateGetAllSubjects.value
    LaunchedEffect(key1 = true) {
        mainViewModel.getAllSubjects()
    }

    /*val state = mainViewModel.stateAddQuestions

    if (state.value is UiState.Loading) {
        Progressbar()
    }

    if (state.value is UiState.Success) {
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }

    if (state.value is UiState.Error) {
        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
    }*/

    /*if (mainViewModel.isEmptyCategories()){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Text(text = "Пока пусто!",color = GrayLabel, textAlign = TextAlign.Center)
        }
    }
    else {
        Column(Modifier.fillMaxWidth()) {

            Log.d("TAG", "MainScreen: ${mainViewModel.getCategories()}")
            LazyColumn {
                items(mainViewModel.getCategories()){
//                    Card(
//                        Modifier
//                            .fillMaxWidth()
//                            .padding(10.dp)) {
                        Text(text = it, color = Primary)
//                    }
                }
            }
        }
    }*/

    if (stateGetAllSubjects is UiState.Success) {
        Column(
            Modifier
                .fillMaxSize()
                .background(GrayIndicator)
                .padding(top = 12.dp, bottom = 130.dp)
        ) {
            LazyColumn {
                items(stateGetAllSubjects.data) { item ->
                    CardSubject(
                        navController = navController,
                        subject = item
                    )
                }
            }
        }
    }

    if (stateGetAllSubjects is UiState.Loading) {
        Progressbar()
    }
    if (stateGetAllSubjects is UiState.Error) {
        Log.d(TAG, "MainScreenError: ${stateGetAllSubjects.message}")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp, start = 10.dp, end = 10.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        PrimaryButton(
            stringId = R.string.add_new_subject
        ) {
            navController.navigate(Screen.AddMyTestScreen.route)
        }
    }

}