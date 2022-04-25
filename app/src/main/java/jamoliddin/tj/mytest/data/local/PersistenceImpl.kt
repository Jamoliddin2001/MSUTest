package jamoliddin.tj.mytest.data.local

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import jamoliddin.tj.mytest.data.model.User
import javax.inject.Inject

const val USER_KEY = "user-model"

class PersistenceImpl @Inject constructor(
    private val preferences: SharedPreferences
): Persistence {

    override fun clearAll(){
        preferences.edit{ clear() }
    }

    override var user: User?
        get() {
            val json = preferences.getString(USER_KEY,null)
            return Gson().fromJson(json, User::class.java)
        }
        set(value){
            val json = Gson().toJson(value)
            preferences.edit{ putString(USER_KEY, json) }
        }

}