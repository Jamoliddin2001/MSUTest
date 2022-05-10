package jamoliddin.tj.mytest_teachers.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jamoliddin.tj.mytest_teachers.data.model.my_questions.Question
import jamoliddin.tj.mytest_teachers.presentation.theme.GrayIndicator
import jamoliddin.tj.mytest_teachers.presentation.theme.GrayLabel
import jamoliddin.tj.mytest_teachers.presentation.theme.Primary

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardQuestion(
    question: Question
) {
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

    when {
        question.varA == question.answer -> stateA.value = true
        question.varB == question.answer -> stateB.value = true
        question.varC == question.answer -> stateC.value = true
        question.varD == question.answer -> stateD.value = true
    }

    Card(
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .defaultMinSize(100.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 10.dp,
        onClick = {
            //TODO
        }
    ) {
        Column() {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = question.question.toString(),
                fontSize = 14.sp,
                color = Primary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = stateA.value,
                    onClick = {
                        /*stateA.value = true
                        stateB.value = false
                        stateC.value = false
                        stateD.value = false*/
                    },
                    modifier = Modifier.padding(start = 10.dp,end = 10.dp),
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Primary,
                        unselectedColor = GrayLabel
                    )
                )
                Text(
                    text = question.varA.toString(),
                    fontSize = 14.sp,
                    color = Primary
                )
            }
            Spacer(modifier = Modifier.padding(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = stateB.value,
                    onClick = {
                        /*stateA.value = false
                        stateB.value = true
                        stateC.value = false
                        stateD.value = false*/
                    },
                    modifier = Modifier.padding(start = 10.dp,end = 10.dp),
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Primary,
                        unselectedColor = GrayLabel
                    )
                )
                Text(
                    text = question.varB.toString(),
                    fontSize = 14.sp,
                    color = Primary
                )
            }
            Spacer(modifier = Modifier.padding(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = stateC.value,
                    onClick = {
                        /*stateA.value = false
                        stateB.value = false
                        stateC.value = true
                        stateD.value = false*/
                    },
                    modifier = Modifier.padding(start = 10.dp,end = 10.dp),
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Primary,
                        unselectedColor = GrayLabel
                    )
                )
                Text(
                    text = question.varC.toString(),
                    fontSize = 14.sp,
                    color = Primary
                )
            }
            Spacer(modifier = Modifier.padding(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = stateD.value,
                    onClick = {
                        /*stateA.value = false
                        stateB.value = false
                        stateC.value = false
                        stateD.value = true*/
                    },
                    modifier = Modifier.padding(start = 10.dp,end = 10.dp),
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Primary,
                        unselectedColor = GrayLabel
                    )
                )
                Text(
                    text = question.varD.toString(),
                    fontSize = 14.sp,
                    color = Primary
                )
            }
            Spacer(modifier = Modifier.padding(8.dp))
        }

    }
}