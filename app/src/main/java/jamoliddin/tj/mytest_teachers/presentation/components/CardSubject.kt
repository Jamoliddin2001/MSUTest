package jamoliddin.tj.mytest_teachers.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import jamoliddin.tj.mytest_teachers.domain.model.Screen
import jamoliddin.tj.mytest_teachers.presentation.screens.main.ShowMyTestViewModel
import jamoliddin.tj.mytest_teachers.presentation.theme.Primary

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardSubject(
    navController: NavController,
    subject: String
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 10.dp)
            .defaultMinSize(minHeight = 100.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 10.dp,
        onClick = {
            navController.navigate(Screen.ShowMyTestScreen.route+"/$subject")
        }
    ) {
        Column(
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = subject,
                fontSize = 14.sp,
                color = Primary
            )
        }
    }

}