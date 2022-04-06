package jamoliddin.tj.mytest.presentation.components

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimationBetweenComposable(content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = true,
        enter = slideInHorizontally(
            initialOffsetX = { fullWidth -> fullWidth },
            animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing)
//        ) + expandVertically(
//            expandFrom = Alignment.CenterVertically,
//            animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
//        ) + fadeIn(initialAlpha = 0.5f),
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { fullWidth -> -fullWidth },
            animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing)
        ),
        content = content,
        initiallyVisible = false
    )
}