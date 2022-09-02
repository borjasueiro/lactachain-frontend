package com.fic.muei.lactachain.di

import com.fic.muei.lactachain.model.*
import com.fic.muei.lactachain.network.*
import com.fic.muei.lactachain.network.BlockchainService
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
                                milkCollectionMapper: Mapper<MilkCollectionDto, MilkCollectionData>,
                                milkDeliveryMapper: Mapper<MilkDeliveryDto, MilkDeliveryData>,
                                receptionSiloMapper: Mapper<ReceptionSiloDto, ReceptionSiloData>,
                                siloReceptionDataMapper: Mapper<ReceptionSiloDto, SiloDataItem>,
                                siloFinalDataMapper: Mapper<FinalSiloDto, SiloDataItem>,
                                finalSiloMapper: Mapper<FinalSiloDto, FinalSiloData>
    ): LactachainRepository {
        return LactachainRepositoryImpl(lactachainService,farmMapper, transporterMapper,
            transportMapper, transportListMapper, milkCollectionMapper,
            milkDeliveryMapper, receptionSiloMapper, siloReceptionDataMapper, siloFinalDataMapper,
            finalSiloMapper, interceptor)
    }

    @Singleton
    @Provides
    fun getChainRepository(chainService: BlockchainService, farmChainMapper: Mapper<FarmChainDto,FarmParcialData>,
                           transportChainMapper: Mapper<TransportChainDto,TransportParcialData>,
                           transvaseChainMapper: Mapper<TransvaseChainDto,TransvaseData>,
                           traceChainMapper: Mapper<TraceChainDto,TraceData>
    ): ChainRepository{
        return ChainRepositoryImpl(chainService, farmChainMapper, transportChainMapper, transvaseChainMapper, traceChainMapper)
    }

}