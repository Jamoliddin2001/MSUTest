package jamoliddin.tj.mytest.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import jamoliddin.tj.mytest.data.model.User
import jamoliddin.tj.mytest.domain.util.PREFERENCES_NAME


class SharedPreferences(private val context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFERENCES_NAME,Context.MODE_PRIVATE)

    fun isAuthorized() = getUser()?.email != null

    fun saveUser(user: User){
        val jsonUser = Gson().toJson(user)
        sharedPreferences.edit{
            putString(USER_KEY, jsonUser)
        }
    }

    fun getUser(): User? {
        val json = sharedPreferences.getString(USER_KEY,null)
        return Gson().fromJson(json, User::class.java)
    }

    fun clearAll(){
        sharedPreferences.edit { clear() }
    }

}