package com.fic.muei.lactachain.di

import com.fic.muei.lactachain.model.*
import com.fic.muei.lactachain.network.*
import com.fic.muei.lactachain.network.model.FarmDto
import com.fic.muei.lactachain.network.model.TransportDto
import com.fic.muei.lactachain.network.model.TransportListDto
import com.fic.muei.lactachain.network.model.TransporterDto
import com.fic.muei.lactachain.utils.Mapper
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
    fun getLactachainRepository(lactachainService:LactachainService,
                                farmMapper: Mapper<FarmDto, FarmData>,
                                interceptor: LactachainAuth,
                                transporterMapper: Mapper<TransporterDto, TransporterData>,
                                transportMapper: Mapper<TransportDto, TransportData>,
                                transportListMapper: Mapper<TransportListDto, TransportListData>): LactachainRepository {
        return LactachainRepositoryImpl(lactachainService,farmMapper, transporterMapper, transportMapper, transportListMapper, interceptor)
    }
}