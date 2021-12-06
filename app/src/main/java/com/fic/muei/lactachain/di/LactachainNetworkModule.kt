package com.fic.muei.lactachain.di

import com.fic.muei.lactachain.model.FarmData
import com.fic.muei.lactachain.model.TransporterData
import com.fic.muei.lactachain.network.*
import com.fic.muei.lactachain.network.model.FarmDto
import com.fic.muei.lactachain.network.model.TransporterDto
import com.fic.muei.lactachain.utils.Mapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LactachainNetworkModule{
    @Singleton
    @Provides
    fun getLactachainService(client:OkHttpClient):LactachainService{
        return Retrofit.Builder()
            .baseUrl("http://192.168.180.210:8080/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create(LactachainService::class.java)
    }
    @Singleton
    @Provides
    fun getFarmMapper(): Mapper<FarmDto, FarmData> = FarmMapper()

    @Singleton
    @Provides
    fun getTransporterMapper(): Mapper<TransporterDto, TransporterData> = TransporterMapper()

    @Singleton
    @Provides
    fun getInterceptorAuth():LactachainAuth{
        return LactachainAuth()
    }
    @Singleton
    @Provides
    fun getLactachainAuth(interceptor: LactachainAuth):OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

}