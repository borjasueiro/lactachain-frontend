package com.fic.muei.lactachain.di

import com.fic.muei.lactachain.model.FarmData
import com.fic.muei.lactachain.network.FarmDto
import com.fic.muei.lactachain.network.FarmMapper
import com.fic.muei.lactachain.network.LactachainService
import com.fic.muei.lactachain.utils.Mapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LactachainNetworkModule{
    @Singleton
    @Provides
    fun getLactachainService():LactachainService{
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.105:8080/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(LactachainService::class.java)
    }
    @Singleton
    @Provides
    fun getFarmMapper(): Mapper<FarmDto, FarmData> = FarmMapper
}