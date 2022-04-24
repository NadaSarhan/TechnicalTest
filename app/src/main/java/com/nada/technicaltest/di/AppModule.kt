package com.nada.technicaltest.di

import android.app.Application
import androidx.room.Room
import com.nada.technicaltest.MainApplication
import com.nada.technicaltest.data.local.AppDataBase
import com.nada.technicaltest.data.repository.ItemRepository
import com.nada.technicaltest.data.repository.ItemRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDataBase(app: Application): AppDataBase {
        return Room.databaseBuilder(
            app,
            AppDataBase::class.java,
            "item_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: AppDataBase) : ItemRepository {
        return ItemRepositoryImpl(db.dao)
    }

    @Provides
    @Singleton
    fun provideAppCoroutinesScope() = CoroutineScope(SupervisorJob())

}