package jamoliddin.tj.mytest_teachers.domain.repository

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import jamoliddin.tj.mytest_teachers.data.model.my_questions.MyQuestions
import jamoliddin.tj.mytest_teachers.domain.UiState
import jamoliddin.tj.mytest_teachers.domain.util.TAG
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MyQuestionsRepository @Inject constructor(
    private val questionCollection: CollectionReference
) {

    private fun countDocument(): Int {
        return questionCollection.get().result.size()
    }

    fun addMyQuestions(myQuestions: MyQuestions) {
        try {
            val id = countDocument().toString()
            questionCollection.document(id).set(myQuestions).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "addMyQuestion: OK -> 200")
                } else Log.d(TAG, "addMyQuestion: ${task.exception?.localizedMessage}")
            }
        } catch (e: Exception) {
            Log.d(TAG, "addMyQuestionCatch: ${e.localizedMessage}")
        }
    }

}