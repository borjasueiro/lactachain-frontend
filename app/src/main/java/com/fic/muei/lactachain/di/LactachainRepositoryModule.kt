package com.fic.muei.lactachain.di

import android.app.Application
import com.fic.muei.lactachain.network.FarmMapper
import com.fic.muei.lactachain.network.LactachainRepositoryImpl
import com.fic.muei.lactachain.network.LactachainService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object LactachainRepositoryModule {
    @Provides
    @Singleton
    fun getLactachainRepository(lactachainService:LactachainService, farmMapper:FarmMapper):LactachainRepositoryImpl{
        return LactachainRepositoryImpl(lactachainService,farmMapper)
    }
}