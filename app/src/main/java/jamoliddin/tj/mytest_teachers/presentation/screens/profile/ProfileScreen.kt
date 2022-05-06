package jamoliddin.tj.mytest_teachers.presentation.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import jamoliddin.tj.mytest_teachers.R
import jamoliddin.tj.mytest_teachers.domain.model.Screen
import jamoliddin.tj.mytest_teachers.presentation.components.common.LogoutDialog
import jamoliddin.tj.mytest_teachers.presentation.screens.profile.components.BottomSheetContent
import jamoliddin.tj.mytest_teachers.presentation.theme.GrayIndicator
import jamoliddin.tj.mytest_teachers.presentation.theme.GrayLabel
import jamoliddin.tj.mytest_teachers.presentation.theme.Headlines
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel = viewModel()
) {

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val user = profileViewModel.getTeacher(context)


    val dialogVisibility = remember {
        mutableStateOf(false)
    }

    val bottomSheetState = rememberBottomSheetScaffoldState()

    LogoutDialog(
        dialogVisibility = dialogVisibility,
        onLogout = {
            dialogVisibility.value = false
            profileViewModel.logout(context)
            navController.navigate(Screen.WelcomeScreen.route){
                popUpTo(0)
            }
        }
    )

    BottomSheetScaffold(
        scaffoldState = bottomSheetState,
        sheetPeekHeight = 0.dp,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        sheetContent = {
            BottomSheetContent(
                navController = navController,
                onItemClicked = { logout ->
                    coroutineScope.revertBottomSheetState(bottomSheetState)
                    if(logout) dialogVisibility.value = true
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp)
                    .size(140.dp)
                    .clip(CircleShape),
                alignment = Alignment.Center,
                painter = painterResource(id = R.drawable.teahcer),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = user?.firstName + "  " + user?.lastName)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${user?.univer} - ${user?.predmet}",
                color = GrayLabel
            )
            Spacer(modifier = Modifier.height(8.dp))
            Divider(modifier = Modifier.height(1.dp), color = GrayIndicator)

        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        IconButton(onClick = {
            coroutineScope.revertBottomSheetState(bottomSheetState)
        }) {
            Icon(
                imageVector = Icons.Outlined.Settings,
                contentDescription = null,
                tint = Headlines
            )
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
private fun CoroutineScope.revertBottomSheetState(bottomSheetState: BottomSheetScaffoldState) {
    launch {
        if (bottomSheetState.bottomSheetState.isCollapsed)
            bottomSheetState.bottomSheetState.expand()
        else bottomSheetState.bottomSheetState.collapse()
    }
}