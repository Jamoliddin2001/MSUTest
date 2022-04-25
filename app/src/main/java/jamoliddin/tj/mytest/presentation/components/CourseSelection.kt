package jamoliddin.tj.mytest.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jamoliddin.tj.mytest.presentation.theme.GrayLabel

@SuppressLint("UnrememberedMutableState")
@Composable
fun CourseSelection(
    choose: (Int) -> Unit = {}
) {
    val listOfCourse = mutableListOf(1,2,3,4)

    var course: Int by remember {
        mutableStateOf(listOfCourse[0])
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    Box(Modifier.fillMaxWidth().padding(start = 10.dp)) {
        Row(modifier = Modifier
            .padding(8.dp)
            .clickable {
                expanded = !expanded
            },
        ) {
            Text(text = "Курс", modifier = Modifier.padding(end = 20.dp), fontSize = 15.sp, color = GrayLabel)
            Text(text = course.toString(), fontSize = 18.sp, modifier = Modifier.padding(end = 8.dp))
            choose(course)
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")
            DropdownMenu(expanded = expanded, onDismissRequest = {
                expanded = false
            }) {
                listOfCourse.forEach { chooseCourse ->
                    DropdownMenuItem(onClick = {
                        expanded = false
                        course = chooseCourse
                    }) {
                        Text(text = chooseCourse.toString())
                    }
                }
            }
        }
    }
}