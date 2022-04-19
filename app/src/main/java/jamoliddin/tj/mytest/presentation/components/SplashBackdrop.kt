package jamoliddin.tj.mytest.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jamoliddin.tj.mytest.R
import jamoliddin.tj.mytest.presentation.theme.Green
import jamoliddin.tj.mytest.presentation.theme.Primary
import jamoliddin.tj.mytest.presentation.theme.myDark

@Composable
fun SplashBackdrop(
    alpha: Float,
    @DrawableRes animationImageId: Int = R.drawable.msu
) {
    Surface(color = Primary) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(220.dp)
                        .alpha(alpha = alpha)
                        .clip(CircleShape),
                    alignment = Alignment.Center,
                    painter = painterResource(id = animationImageId),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "developed by Jamoliddin",
                    modifier = Modifier.alpha(alpha).fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,
                    color = Color(0xFF315891)
                )
            }
        }
    }
}