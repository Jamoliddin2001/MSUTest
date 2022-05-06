package jamoliddin.tj.mytest_teachers.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import jamoliddin.tj.mytest_teachers.data.model.Teacher
import jamoliddin.tj.mytest_teachers.domain.util.PREFERENCES_NAME


class SharedPreferences(private val context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFERENCES_NAME,Context.MODE_PRIVATE)

    fun isAuthorized() = getTeacher()?.email != null

    fun saveTeacher(teacher: Teacher){
        val jsonUser = Gson().toJson(teacher)
        sharedPreferences.edit{
            putString(USER_KEY, jsonUser)
        }
    }

    fun getTeacher(): Teacher? {
        val json = sharedPreferences.getString(USER_KEY,null)
        return Gson().fromJson(json, Teacher::class.java)
    }

    fun clearAll(){
        sharedPreferences.edit { clear() }
    }

}