package com.example.oophandbook.di

import com.example.oophandbook.data.repository.BookmarkRepositoryImpl
import com.example.oophandbook.data.repository.TopicRepositoryImpl
import com.example.oophandbook.data.repository.UnitRepositoryImpl
import com.example.oophandbook.data.repository.fake.FakeBookmarkRepository
import com.example.oophandbook.data.repository.fake.FakeTopicRepository
import com.example.oophandbook.data.repository.fake.FakeUnitRepository
import com.example.oophandbook.domain.repository.BookmarkRepository
import com.example.oophandbook.domain.repository.TopicRepository
import com.example.oophandbook.domain.repository.UnitRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryBindingModule {

    @Binds
    fun bindTopicsRepository(impl: TopicRepositoryImpl): TopicRepository

    @Binds
    fun bindUnitRepository(impl: UnitRepositoryImpl): UnitRepository

    @Binds
    fun bindBookmarkRepository(impl: BookmarkRepositoryImpl): BookmarkRepository
}