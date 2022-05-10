package jamoliddin.tj.mytest_teachers.presentation.screens.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import jamoliddin.tj.mytest_teachers.data.model.my_questions.MyQuestions
import jamoliddin.tj.mytest_teachers.domain.UiState
import jamoliddin.tj.mytest_teachers.domain.util.MY_QUESTIONS
import jamoliddin.tj.mytest_teachers.domain.util.TAG
import jamoliddin.tj.mytest_teachers.domain.util.TEACHERS

class ShowMyTestViewModel: ViewModel() {

    private val questionCollection = FirebaseFirestore.getInstance().collection(TEACHERS)
        .document(FirebaseAuth.getInstance().uid.toString()).collection(MY_QUESTIONS)

    private val _stateShowTest: MutableState<UiState<MyQuestions>> = mutableStateOf(UiState.Idle)
    val stateShowTest: State<UiState<MyQuestions>> = _stateShowTest

    fun getTest(subject: String){
        _stateShowTest.value = UiState.Loading
        var myTest: MyQuestions
        questionCollection.document(subject).get().addOnCompleteListener { task ->
            if(task.isSuccessful){
                val data = task.result.data.toString()
                try {
                    myTest = Gson().fromJson(data, MyQuestions::class.java)
                    _stateShowTest.value = UiState.Success(data = myTest)
                } catch (e: JsonSyntaxException){
                    _stateShowTest.value = UiState.Error(e.localizedMessage?.toString() ?: "JsonSyntaxException")
                    Log.d(TAG, "getTest: ${e.localizedMessage}")
                }
            } else Log.d(TAG, "getTestError: ${task.exception?.localizedMessage}")
        }
    }

}