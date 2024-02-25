package com.practical.divy.hilt.di

import android.content.Context
import androidx.room.Room
import com.practical.divy.R
import com.practical.divy.database.AppDao
import com.practical.divy.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "${context.getString(R.string.app_name)}.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase): AppDao = db.appDao()
}
