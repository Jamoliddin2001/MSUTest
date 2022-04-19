package jamoliddin.tj.mytest.domain.repository

import com.google.firebase.firestore.CollectionReference
import jamoliddin.tj.mytest.data.model.User
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthRepository @Inject constructor(
    private val userList: CollectionReference
) {

    fun register(user: User){
        try {
            userList.document(user.id).set(user)
        } catch (e:Exception){
            e.printStackTrace()
        }
    }

}