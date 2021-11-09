package com.fic.muei.lactachain.di

import com.fic.muei.lactachain.network.FarmMapper
import com.fic.muei.lactachain.network.LactachainService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object LactachainNetworkModule{
    @Provides
    @Singleton
    fun getLactachainService():LactachainService{
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.110:8000/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(LactachainService::class.java)
    }
    @Provides
    @Singleton
    fun getFarmMapper():FarmMapper{
        return FarmMapper()
    }
}