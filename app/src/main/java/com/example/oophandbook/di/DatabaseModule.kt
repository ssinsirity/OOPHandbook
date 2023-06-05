package com.example.oophandbook.di

import android.content.Context
import androidx.room.Room
import com.example.oophandbook.data.db.OOPDatabase
import com.example.oophandbook.data.db.callback.PrepopulateDatabaseCallback
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
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context = context,
        klass = OOPDatabase::class.java,
        name = "oop_database"
    ).addCallback(PrepopulateDatabaseCallback(context)).build()

    @Provides
    fun provideBookmarkDao(db: OOPDatabase) = db.bookmarkDao()

    @Provides
    fun provideContentDao(db: OOPDatabase) = db.contentDao()

    @Provides
    fun provideTopicDao(db: OOPDatabase) = db.topicDao()

    @Provides
    fun provideUnitDao(db: OOPDatabase) = db.unitDao()
}