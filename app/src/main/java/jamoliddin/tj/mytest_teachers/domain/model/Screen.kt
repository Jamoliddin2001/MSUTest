package jamoliddin.tj.mytest_teachers.domain.model


sealed class Screen(val route: String){

    object SplashScreen: Screen("SplashScreen")
    object WelcomeScreen: Screen("WelcomeScreen")
    object LoginScreen: Screen("LoginScreen")
    object RegisterScreen: Screen("RegisterScreen")
    object RegisterScreenPersonalData: Screen("RegisterScreenPersonalData")
    object MainScreen: Screen("MainScreen")
    object TestScreen: Screen("TestScreen")
    object AddMyTestScreen: Screen("AddMyTestScreen")
    object AddQuestionsScreen: Screen("AddQuestionsScreen")
    object MyTestsScreen: Screen("MyTestsScreen")
    object ProfileScreen: Screen("ProfileScreen")
    object UpdatePersonalDataScreen: Screen("UpdatePersonalDataScreen")
    object CategoryScreen: Screen("CategoryScreen")
    object ResultScreen: Screen("ResultScreen")
    object PersonalDataScreen: Screen("PersonalDataScreen")
    object InfoScreen: Screen("InfoScreen")

}
