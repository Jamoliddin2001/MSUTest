package jamoliddin.tj.mytest.presentation.components

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

@Composable
fun NapravlenieSelection() {

    val list = mutableListOf("ПМиИ", "ХФММ", "Геология", "ГМУ", "МО", "Лингвистика")

    var napName: String by remember { mutableStateOf(list[0]) }
    var expanded by remember { mutableStateOf(false) }

    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Row(modifier = Modifier
            .padding(24.dp)
            .clickable {
                expanded = !expanded
            }
            .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = napName, fontSize = 18.sp, modifier = Modifier.padding(end = 8.dp))
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")
            DropdownMenu(expanded = expanded, onDismissRequest = {
                expanded = false
            }) {
                list.forEach { napravlenie ->
                    DropdownMenuItem(onClick = {
                        expanded = false
                        napName = napravlenie
                    }) {
                        Text(text = napravlenie)
                    }
                }
            }
        }
    }

}