package jamoliddin.tj.mytest.data.model

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val rating: Float
){
    constructor(): this("","","",0f)
}
