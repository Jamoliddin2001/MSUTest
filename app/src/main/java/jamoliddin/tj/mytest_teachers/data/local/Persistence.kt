package jamoliddin.tj.mytest_teachers.data.local

import jamoliddin.tj.mytest_teachers.data.model.Teacher

interface Persistence {
    fun clearAll()

    var user: Teacher?
}