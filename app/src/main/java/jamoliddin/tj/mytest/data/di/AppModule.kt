package jamoliddin.tj.mytest.data.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
    fun provideQuestions(
        fireStore: FirebaseFirestore
    ) = fireStore.collection("Questions")

}