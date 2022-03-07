package com.fic.muei.lactachain.di

import com.fic.muei.lactachain.model.*
import com.fic.muei.lactachain.network.*
import com.fic.muei.lactachain.network.model.*
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
                                transportListMapper: Mapper<TransportListDto, TransportListData>,
                                MilkCollectionMapper: Mapper<MilkCollectionDto, MilkCollectionData>,
                                MilkDeliveryMapper: Mapper<MilkDeliveryDto, MilkDeliveryData>,
                                ReceptionSiloMapper: Mapper<ReceptionSiloDto, ReceptionSiloData>,
                                FinalSiloMapper: Mapper<FinalSiloDto, FinalSiloData>
    ): LactachainRepository {
        return LactachainRepositoryImpl(lactachainService,farmMapper, transporterMapper,
            transportMapper, transportListMapper, MilkCollectionMapper,
            MilkDeliveryMapper, ReceptionSiloMapper, FinalSiloMapper, interceptor)
    }

}