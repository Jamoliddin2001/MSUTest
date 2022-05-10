package jamoliddin.tj.mytest_teachers.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.lifecycle.viewmodel.compose.viewModel
import jamoliddin.tj.mytest_teachers.presentation.screens.main.MainViewModel
import jamoliddin.tj.mytest_teachers.presentation.theme.PopularChipTextColor
import jamoliddin.tj.mytest_teachers.presentation.theme.Primary

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DeleteDialog(
    dialogVisibility: MutableState<Boolean>,
    onDelete: () -> Unit,
    mainViewModel: MainViewModel = viewModel()
) {

    AnimatedVisibility(visible = dialogVisibility.value) {
        AlertDialog(
            onDismissRequest = { dialogVisibility.value = false },
            title = {
                Text(
                    text = "Удаление"
                )
            },
            text = {
                Text(text = "Вы действительно хотите удалить?")
            },
            confirmButton = {
                TextButton(
                    onClick = onDelete
                ) {
                    Text(text = "Да", color = PopularChipTextColor)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        dialogVisibility.value = false
                        mainViewModel.getAllSubjects()
                    }
                ) {
                    Text(text = "Отмена", color = Primary)
                }
            }
        )
    }

}