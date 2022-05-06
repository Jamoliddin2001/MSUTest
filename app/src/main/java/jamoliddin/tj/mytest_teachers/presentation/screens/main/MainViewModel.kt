package jamoliddin.tj.mytest_teachers.presentation.screens.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import jamoliddin.tj.mytest_teachers.data.model.my_questions.MyQuestions
import jamoliddin.tj.mytest_teachers.domain.UiState
import jamoliddin.tj.mytest_teachers.domain.repository.MyQuestionsRepository
import jamoliddin.tj.mytest_teachers.domain.util.*
import javax.inject.Inject

class MainViewModel : ViewModel() {

    private val questionCollection = FirebaseFirestore.getInstance().collection(TEACHERS)
        .document(FirebaseAuth.getInstance().uid.toString()).collection(MY_QUESTIONS)

    private val _stateAddQuestions: MutableState<UiState<Any>> =
        mutableStateOf(UiState.Idle)
    val stateAddQuestions: State<UiState<Any>> = _stateAddQuestions

    fun addMyQuestions(myQuestions: MyQuestions) {
        _stateAddQuestions.value = UiState.Loading
        questionCollection.document(myQuestions.subject.toString()).set(myQuestions).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _stateAddQuestions.value = UiState.Success(data = task.result)
            } else _stateAddQuestions.value = UiState.Error(message = task.exception?.localizedMessage.toString())
        }
    }

    private val _stateGetAllSubjects: MutableState<UiState<List<String>>> = mutableStateOf(UiState.Idle)
    val stateGetAllSubjects: State<UiState<List<String>>> = _stateGetAllSubjects


    fun getAllSubjects() {
        _stateGetAllSubjects.value = UiState.Loading
        val listSubjects = mutableListOf<String>()
        questionCollection.get().addOnCompleteListener { task ->
            if (task.isSuccessful){
                task.result.documents.forEach { index ->
                    listSubjects.add(index.id)
                }
                _stateGetAllSubjects.value = UiState.Success(data = listSubjects)
            } else _stateGetAllSubjects.value = UiState.Error(task.exception?.localizedMessage.toString())
        }
    }


    /*private val db = FirebaseFirestore.getInstance()

    fun sizeOfDocument(): Int{
        var size = 0
        db.collection("CATEGORIES").get().addOnCompleteListener{ task ->
            if (task.isSuccessful){
                size = task.result.size()
            } else task.exception?.let {
                Log.d("TAG", "sizeOfDocument: ${it.localizedMessage}")
            }
        }
        return size
    }

    fun isEmptyCategories(): Boolean {
        var result = false
        db.collection(CATEGORIES).get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                result = task.result.isEmpty
            } else {
                Log.d("TAG", "isEmptyCategories: ${task.exception?.localizedMessage}")
            }
        }

        return result
    }

    fun getCategories(): List<String> {
        val list = mutableListOf<String>()

        db.collection(CATEGORIES).get().addOnCompleteListener { task ->
            if(task.isSuccessful){
                task.result.documents.forEach { index ->
                    list.add(index.id)
                }
                Log.d("TAG", "getCategories: $list")
            } else task.exception?.let {
                Log.d("TAG", "saveTestError: ${it.localizedMessage}")
            }
        }

        
        return list.toList()
    }

    fun testIncrement(){
        val city = hashMapOf(
            "name" to "Los Angeles",
            "state" to "CA",
            "country" to "USA"
        )
        val count = db.collection("Results").get().addOnCompleteListener{
            if (it.isSuccessful){
                Log.d("TAG", "testIncrement: ${it.result.size()}")
            }
        }
//        Log.d("TAG", "testIncrement: $count")
//        db.collection("Results").document("").set(city)

    }

    fun saveTest() {
        db.collection(CATEGORIES).get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "saveUserData: ${task.result.documents}")
                } else task.exception?.let {
                    Log.d("TAG", "saveTestError: ${it.localizedMessage}")
                }
            }
    }*/

}