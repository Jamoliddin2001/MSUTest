package jamoliddin.tj.mytest_teachers.presentation.components

import androidx.compose.animation.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimationBetweenComposable(content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = true,
        enter = slideInHorizontally(initialOffsetX = {it}),
        exit = slideOutHorizontally(targetOffsetX = {it}),
        content = content,
        initiallyVisible = true
    )
}