package jamoliddin.tj.mytest

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App:Application() {

    lateinit var unauthenticatedHandled: MutableState<Boolean>

    override fun onCreate() {
        super.onCreate()
        unauthenticatedHandled = mutableStateOf(false)
    }
}