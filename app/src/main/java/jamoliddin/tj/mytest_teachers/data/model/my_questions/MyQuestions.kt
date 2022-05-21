package jamoliddin.tj.mytest_teachers.data.model.my_questions

data class MyQuestions(
    val countQuestion: Int? = 0,
    val course: Int? = 0,
    val minRating3: Int? = 0,
    val minRating4: Int? = 0,
    val minRating5: Int? = 0,
    val subject: String? = "",
    val questions: List<Question>,
    val teacherID: String,
    val timeToDo: Int
){
    constructor():this(0,0,0,0,0,"", listOf(Question()),"",0)
}
