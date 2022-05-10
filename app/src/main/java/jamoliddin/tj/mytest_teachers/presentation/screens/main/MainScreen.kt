package jamoliddin.tj.mytest_teachers.presentation.screens.main

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import jamoliddin.tj.mytest_teachers.presentation.components.DeleteDialog
import jamoliddin.tj.mytest_teachers.presentation.components.PrimaryButton
import jamoliddin.tj.mytest_teachers.presentation.components.Progressbar
import jamoliddin.tj.mytest_teachers.presentation.components.common.LogoutDialog
import jamoliddin.tj.mytest_teachers.presentation.screens.auth.AuthViewModel
import jamoliddin.tj.mytest_teachers.presentation.theme.*

@OptIn(ExperimentalMaterialApi::class)
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

    val stateGetAllSubjects = mainViewModel.stateGetAllSubjects.value
    LaunchedEffect(key1 = true) {
        mainViewModel.getAllSubjects()
    }
    val stateDelete = mainViewModel.stateDeleteSubject.value

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

    val dialogVisibility = remember {
        mutableStateOf(false)
    }

    var subject: String? = ""

    DeleteDialog(
        dialogVisibility = dialogVisibility,
        onDelete = {
            dialogVisibility.value = false
            mainViewModel.deleteSubject(subject.toString())
        }
    )

    if(stateDelete is UiState.Success){
        LaunchedEffect(key1 = true){
            mainViewModel.getAllSubjects()
        }
    }

    if (stateGetAllSubjects is UiState.Success) {
        Column(
            Modifier
                .fillMaxSize()
                .background(GrayIndicator)
                .padding(top = 12.dp, bottom = 130.dp)
        ) {
            if (stateGetAllSubjects.data.isNotEmpty()) {
                LazyColumn {

                    itemsIndexed(
                        items = stateGetAllSubjects.data,
                        key = { _, item ->
                            item.hashCode()
                        }
                    ) { index, item ->
                        val state = rememberDismissState(
                            confirmStateChange = {
                                if (it == DismissValue.DismissedToStart) {
                                    subject = item
                                    dialogVisibility.value = true
                                }
                                true
                            }
                        )

                        SwipeToDismiss(
                            state = state,
                            background = {
                                val color = when (state.dismissDirection) {
                                    DismissDirection.StartToEnd -> Color.Transparent
                                    DismissDirection.EndToStart -> PopularChipTextColor
                                    null -> Color.Transparent
                                }

                                Column(Modifier.fillMaxSize()) {
                                    Box(
                                        modifier = Modifier
                                            .background(color = color)
                                            .fillMaxSize()
                                            .padding(8.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Delete,
                                            contentDescription = "Delete",
                                            tint = Color.White,
                                            modifier = Modifier.align(Alignment.CenterEnd)
                                        )
                                    }
                                }
                            },
                            dismissContent = {
                                CardSubject(navController = navController, subject = item)
                            },
                            directions = setOf(DismissDirection.EndToStart)
                        )
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

    if(stateDelete is UiState.Loading){
        Progressbar()
    }
    if(stateDelete is UiState.Error){
        Toast.makeText(context, stateDelete.message, Toast.LENGTH_SHORT).show()
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