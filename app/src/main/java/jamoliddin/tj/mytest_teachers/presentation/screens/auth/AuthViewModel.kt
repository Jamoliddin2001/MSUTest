package jamoliddin.tj.mytest_teachers.presentation.screens.auth

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import dagger.hilt.android.lifecycle.HiltViewModel
import jamoliddin.tj.mytest_teachers.data.local.SharedPreferences
import jamoliddin.tj.mytest_teachers.data.model.Teacher
import jamoliddin.tj.mytest_teachers.domain.util.TEACHERS
import jamoliddin.tj.mytest_teachers.domain.util.USERS
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

    fun saveUserData(teacher: Teacher, context: Context) {
        _saveUserData.value = AuthState.Loading
        val uid = FirebaseAuth.getInstance().uid.toString()
        FirebaseFirestore.getInstance().collection(TEACHERS).document(uid).set(teacher)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val sharedPreferences = SharedPreferences(context)
                    sharedPreferences.saveTeacher(teacher)
                    val newUser = sharedPreferences.getTeacher()
                    Log.d("TAG", "saveUserData: $newUser")
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
                    getTeacherFromLogin(uid,context)
                } else {
                    task.exception?.let {
                        _loginState.value = AuthState.AuthError(it.localizedMessage)
                    }
                }
            }
    }

    private fun getTeacherFromLogin(uid: String, context: Context): Teacher? {
        var result: Teacher? = Teacher()
        FirebaseFirestore.getInstance().collection(TEACHERS).document(uid).get(Source.SERVER).addOnCompleteListener{ task ->
            if(task.isSuccessful){
                _loginState.value = AuthState.Success
                val data = task.result.data.toString()
                if(data == "null") _loginState.value = AuthState.AuthError("Вы не являетесь преподавателем! :(")
                else try{
                    result = Gson().fromJson(data, Teacher::class.java)
                    val sharedPreferences = SharedPreferences(context)
                    result?.let { sharedPreferences.saveTeacher(it) }
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