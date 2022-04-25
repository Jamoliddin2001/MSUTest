package jamoliddin.tj.mytest.data.local

import jamoliddin.tj.mytest.data.model.User

interface Persistence {
    fun clearAll()

    var user: User?
}