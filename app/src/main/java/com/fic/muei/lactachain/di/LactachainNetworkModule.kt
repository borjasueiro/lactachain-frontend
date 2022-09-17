package com.fic.muei.lactachain.di

import com.fic.muei.lactachain.model.*
import com.fic.muei.lactachain.network.*
import com.fic.muei.lactachain.network.model.*
import com.fic.muei.lactachain.utils.Mapper
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.166:8080/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create(LactachainService::class.java)
    }
    @Singleton
    @Provides
    fun getChainService(client:OkHttpClient):BlockchainService{
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.166:8888/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
            .create(BlockchainService::class.java)
    }
    @Singleton
    @Provides
    fun getFarmMapper(): Mapper<FarmDto, FarmData> = FarmMapper()

    @Singleton
    @Provides
    fun getTransporterMapper(): Mapper<TransporterDto, TransporterData> = TransporterMapper()

    @Singleton
    @Provides
    fun getTransportMapper(): Mapper<TransportDto, TransportData> = TransportMapper()

    @Singleton
    @Provides
    fun getTransportListMapper(): Mapper<TransportListDto, TransportListData> = TransportListMapper()

    @Singleton
    @Provides
    fun getMilkCollectionMapper(): Mapper<MilkCollectionDto, MilkCollectionData> = MilkCollectionMapper()

    @Singleton
    @Provides
    fun getMilkDeliveryMapper(): Mapper<MilkDeliveryDto, MilkDeliveryData> = MilkDeliveryMapper()

    @Singleton
    @Provides
    fun getReceptionSiloMapper(): Mapper<ReceptionSiloDto, ReceptionSiloData> = ReceptionSiloMapper()

    @Singleton
    @Provides
    fun getFinalSiloMapper(): Mapper<FinalSiloDto, FinalSiloData> = FinalSiloMapper()

    @Singleton
    @Provides
    fun getFinalDataMapper(): Mapper<FinalSiloDto, SiloDataItem> = SiloFinalDataMapper()

    @Singleton
    @Provides
    fun getReceptionDataMapper(): Mapper<ReceptionSiloDto, SiloDataItem> = SiloReceptionDataMapper()

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


    @Singleton
    @Provides
    fun getFarmChainMapper(): Mapper<FarmChainDto, FarmParcialData> = FarmChainMapper()

    @Singleton
    @Provides
    fun getTransportChainMapper(): Mapper<TransportChainDto,TransportParcialData> = TransportChainMapper()

    @Singleton
    @Provides
    fun getTransvaseChainMapper(): Mapper<TransvaseChainDto, TransvaseData> = TransvaseChainMapper()

    @Singleton
    @Provides
    fun getTraceChainMapper(): Mapper<TraceChainDto, TraceData> = TraceChainMapper()


}