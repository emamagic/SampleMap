package com.geo.sampleapplication.di

import com.geo.sampleapplication.ui.fragment.pagetwo.PageTwoRepository
import com.geo.sampleapplication.ui.fragment.pagetwo.PageTwoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BinderModule {

    @Binds
    abstract fun bindUnSplashRepository(pageOneRepositoryImpl: PageTwoRepositoryImpl): PageTwoRepository

}