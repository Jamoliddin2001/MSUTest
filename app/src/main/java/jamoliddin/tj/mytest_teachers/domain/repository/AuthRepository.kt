package jamoliddin.tj.mytest_teachers.domain.repository

import com.google.firebase.firestore.CollectionReference
import jamoliddin.tj.mytest_teachers.data.model.Teacher
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthRepository @Inject constructor(
    private val userList: CollectionReference
) {

    fun register(user: Teacher){
        try {
//            user.id?.let { userList.document(it).set(user) }
        } catch (e:Exception){
            e.printStackTrace()
        }
    }

}