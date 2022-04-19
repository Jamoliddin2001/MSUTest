package jamoliddin.tj.mytest.presentation.screens.auth

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel: ViewModel() {

    private val _authState: MutableState<AuthState> = mutableStateOf(AuthState.Idle)
    val authState: State<AuthState> = _authState

    fun register(email: String, password:String){
        _authState.value = AuthState.Loading
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener{ task->
            if(task.isSuccessful)
                _authState.value = AuthState.Success
            else {
                task.exception?.let {
                    _authState.value = AuthState.AuthError(it.localizedMessage)
                }
            }
        }
    }
}


sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    object Success : AuthState()
    class AuthError(val message: String? = null) : AuthState()
}