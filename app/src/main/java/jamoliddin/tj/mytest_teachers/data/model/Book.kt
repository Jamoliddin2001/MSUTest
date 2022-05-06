package jamoliddin.tj.mytest_teachers.data.model

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val rating: Float
){
    constructor(): this("","","",0f)
}
