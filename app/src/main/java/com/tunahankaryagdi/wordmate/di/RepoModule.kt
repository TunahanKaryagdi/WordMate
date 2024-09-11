package com.tunahankaryagdi.wordmate.di

import com.tunahankaryagdi.wordmate.data.WordRepository
import com.tunahankaryagdi.wordmate.data.WordRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideWordRepository(wordRepositoryImpl: WordRepositoryImpl): WordRepository
}
