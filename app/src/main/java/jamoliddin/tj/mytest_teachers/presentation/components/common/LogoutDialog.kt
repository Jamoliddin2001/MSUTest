package jamoliddin.tj.mytest_teachers.presentation.components.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import jamoliddin.tj.mytest_teachers.R
import jamoliddin.tj.mytest_teachers.presentation.theme.Primary

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LogoutDialog(
    dialogVisibility: MutableState<Boolean>,
    onLogout: () -> Unit
) {
    AnimatedVisibility(visible = dialogVisibility.value) {
        AlertDialog(
            onDismissRequest = { dialogVisibility.value = false },
            title = {
                Text(
                    text = stringResource(id = R.string.logout_title)
                )
            },
            text = {
                Text(text = stringResource(id = R.string.logout_body))
            },
            confirmButton = {
                TextButton(onClick = onLogout) {
                    Text(text = stringResource(id = R.string.logout_label),color = Primary)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    dialogVisibility.value = false
                }) {
                    Text(text = stringResource(id = R.string.cancel), color = Primary)
                }
            }
        )
    }
}