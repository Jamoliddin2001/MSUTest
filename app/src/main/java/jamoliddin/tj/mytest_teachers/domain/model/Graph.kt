package jamoliddin.tj.mytest_teachers.domain.model

sealed class Graph(val route: String){
    object Login: Graph("LoginGraph")
    object Register: Graph("RegisterGraph")
    object Welcome: Graph("WelcomeGraph")
    object Main: Graph("MainGraph")
    object Profile: Graph("ProfileGraph")
    object MyTests: Graph("MyTestGraph")
}
