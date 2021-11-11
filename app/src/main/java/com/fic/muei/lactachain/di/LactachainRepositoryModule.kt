package com.fic.muei.lactachain.di

import android.app.Application
import com.fic.muei.lactachain.model.LactachainRepository
import com.fic.muei.lactachain.network.FarmMapper
import com.fic.muei.lactachain.network.LactachainRepositoryImpl
import com.fic.muei.lactachain.network.LactachainService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LactachainRepositoryModule {
    @Singleton
    @Provides
    fun getLactachainRepository(lactachainService:LactachainService, farmMapper:FarmMapper): LactachainRepository {
        return LactachainRepositoryImpl(lactachainService,farmMapper)
    }
}