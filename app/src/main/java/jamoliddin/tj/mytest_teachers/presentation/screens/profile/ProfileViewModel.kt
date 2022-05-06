package jamoliddin.tj.mytest_teachers.presentation.screens.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jamoliddin.tj.mytest_teachers.data.local.SharedPreferences
import jamoliddin.tj.mytest_teachers.data.model.Teacher
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(

) : ViewModel() {


    fun getTeacher(context: Context): Teacher? {
        val sharedPreferences = SharedPreferences(context)
        return sharedPreferences.getTeacher()
    }

    fun logout(context: Context){
        val sharedPreferences = SharedPreferences(context)
        sharedPreferences.clearAll()
    }
}