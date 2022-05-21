package jamoliddin.tj.mytest_teachers.presentation.screens.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import jamoliddin.tj.mytest_teachers.data.model.my_questions.MyQuestions
import jamoliddin.tj.mytest_teachers.data.model.my_questions.Question
import jamoliddin.tj.mytest_teachers.data.model.subjects.Subject
import jamoliddin.tj.mytest_teachers.domain.UiState
import jamoliddin.tj.mytest_teachers.domain.util.MY_QUESTIONS
import jamoliddin.tj.mytest_teachers.domain.util.SUBJECTS
import jamoliddin.tj.mytest_teachers.domain.util.TAG
import jamoliddin.tj.mytest_teachers.domain.util.TEACHERS

class AddMyTestViewModel : ViewModel() {

    val uid = FirebaseAuth.getInstance().uid

    private val collectionSubjects = FirebaseFirestore.getInstance().collection(SUBJECTS)

    private val questionCollection = FirebaseFirestore.getInstance().collection(TEACHERS)
        .document(FirebaseAuth.getInstance().uid.toString()).collection(MY_QUESTIONS)

    val arrayOfQuestions = arrayListOf<Question>()

    private val _stateAddQuestions: MutableState<UiState<Any>> =
        mutableStateOf(UiState.Idle)
    val stateAddQuestions: State<UiState<Any>> = _stateAddQuestions

    fun addMyQuestions(myQuestions: MyQuestions, isUpdate: Boolean = false) {
        _stateAddQuestions.value = UiState.Loading
        questionCollection.document(myQuestions.subject.toString()).set(myQuestions).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "addMyQuestions: questionSuccessAdd")
                if(!isUpdate) addSubject(Subject(subject = myQuestions.subject, teacherID = myQuestions.teacherID))
                else _stateAddQuestions.value = UiState.Success(data = "SUCCESS")
            } else _stateAddQuestions.value = UiState.Error(message = task.exception?.localizedMessage.toString())
        }
    }

    private fun addSubject(subject: Subject){
        collectionSubjects.document().set(subject).addOnCompleteListener { task ->
            if(task.isSuccessful){
                _stateAddQuestions.value = UiState.Success(data = "SUCCESS")
            } else _stateAddQuestions.value = UiState.Error(message = task.exception?.localizedMessage.toString())
        }
    }

}