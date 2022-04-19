package jamoliddin.tj.mytest.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jamoliddin.tj.mytest.R
import jamoliddin.tj.mytest.presentation.theme.Primary

@Composable
internal fun PrimaryButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    @StringRes stringId: Int = R.string.next,
    textSize: TextUnit = 14.sp,
    backgroundColor: Color = Primary,
    contentColor: Color = Color.White,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        enabled = enabled,
        onClick = onClick,
        shape = RoundedCornerShape(size = 8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        )
    ) {
        Text(
            text = stringResource(id = stringId),
            fontSize = textSize,
            style = TextStyle(fontWeight = FontWeight.Medium),
        )
    }
}