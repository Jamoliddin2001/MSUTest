package jamoliddin.tj.mytest.presentation.screens.auth

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import dagger.hilt.android.lifecycle.HiltViewModel
import jamoliddin.tj.mytest.data.local.SharedPreferences
import jamoliddin.tj.mytest.data.model.User
import jamoliddin.tj.mytest.domain.util.USERS
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {

    private val _authState: MutableState<AuthState> = mutableStateOf(AuthState.Idle)
    val authState: State<AuthState> = _authState

    private val _loginState: MutableState<AuthState> = mutableStateOf(AuthState.Idle)
    val loginState: State<AuthState> = _loginState

    fun register(email: String, password: String) {
        _authState.value = AuthState.Loading
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful)
                    _authState.value = AuthState.Success
                else {
                    task.exception?.let {
                        _authState.value = AuthState.AuthError(it.localizedMessage)
                    }
                }
            }
    }

    private val _saveUserData: MutableState<AuthState> = mutableStateOf(AuthState.Idle)
    val saveUserState: State<AuthState> = _saveUserData

    fun saveUserData(user: User, context: Context) {
        _saveUserData.value = AuthState.Loading
        val uid = FirebaseAuth.getInstance().uid.toString()
        FirebaseFirestore.getInstance().collection(USERS).document(uid).set(user)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val sharedPreferences = SharedPreferences(context)
                    sharedPreferences.saveUser(user)
                    val newUser = sharedPreferences.getUser()
                    _saveUserData.value = AuthState.Success
                } else task.exception?.let {
                    _saveUserData.value = AuthState.AuthError(it.localizedMessage)
                }
            }
    }


    fun login(email: String, password: String,context: Context) {
        _loginState.value = AuthState.Loading
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = FirebaseAuth.getInstance().uid.toString()
                    getUserFromLogin(uid,context)
                } else {
                    task.exception?.let {
                        _loginState.value = AuthState.AuthError(it.localizedMessage)
                    }
                }
            }
    }

    private fun getUserFromLogin(uid: String, context: Context): User? {
        var result: User? = User()
        FirebaseFirestore.getInstance().collection(USERS).document(uid).get(Source.SERVER).addOnCompleteListener{ task ->
            if(task.isSuccessful){
                _loginState.value = AuthState.Success
                val data = task.result.data.toString()
                try{
                    result = Gson().fromJson(data, User::class.java)
                    val sharedPreferences = SharedPreferences(context)
                    result?.let { sharedPreferences.saveUser(it) }
                } catch (e: JsonSyntaxException){
                    Log.d("TAG", "getUserFromLogin: ${e.localizedMessage}")
                }
            } else {
                task.exception?.let {
                    _loginState.value = AuthState.AuthError(it.localizedMessage)
                }
            }
        }

        return result
    }
}


sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    object Success : AuthState()
    class AuthError(val message: String? = null) : AuthState()
}