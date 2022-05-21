package jamoliddin.tj.mytest_teachers.domain

sealed class UiState<out T: Any?> {
    data class Success<T : Any?>(val data: T) : UiState<T>()
    data class Error<T : Any?>(val message: String) : UiState<T>()
    object Loading: UiState<Nothing>()
    object Idle: UiState<Nothing>()
}