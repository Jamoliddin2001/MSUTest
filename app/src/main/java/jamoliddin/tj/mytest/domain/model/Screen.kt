package jamoliddin.tj.mytest.domain.model

sealed class Screen(val route: String){

    object SplashScreen: Screen("SplashScreen")
    object WelcomeScreen: Screen("WelcomeScreen")
    object LoginScreen: Screen("LoginScreen")
    object RegisterScreen: Screen("RegisterScreen")
    object RegisterScreenPersonalData: Screen("RegisterScreenPersonalData")
    object MainScreen: Screen("MainScreen")

}
