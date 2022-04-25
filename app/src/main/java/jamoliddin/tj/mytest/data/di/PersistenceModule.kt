package jamoliddin.tj.mytest.data.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jamoliddin.tj.mytest.data.local.Persistence
import jamoliddin.tj.mytest.data.local.PersistenceImpl
import jamoliddin.tj.mytest.domain.util.PREFERENCES_NAME

@Module
@InstallIn(SingletonComponent::class)
interface PersistenceModuleBinds {
    @Binds
    fun bindPersistence(persistenceImpl: PersistenceImpl): Persistence
}

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE)
    }
}