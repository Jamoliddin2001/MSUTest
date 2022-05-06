package jamoliddin.tj.mytest_teachers.data.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jamoliddin.tj.mytest_teachers.domain.util.MY_QUESTIONS
import jamoliddin.tj.mytest_teachers.domain.util.TEACHERS
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirestoreInstance() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideUserCreate(
        fireStore:FirebaseFirestore
    ) = fireStore.collection("Users")

    @Provides
    @Singleton
    fun provideMyQuestions(
        fireStore: FirebaseFirestore
    ) = fireStore.collection(TEACHERS)

}