package com.fic.muei.lactachain.network

import com.fic.muei.lactachain.model.*
import com.fic.muei.lactachain.model.exceptions.LactachainException
import com.fic.muei.lactachain.network.model.FarmChainDto
import com.fic.muei.lactachain.network.model.TraceChainDto
import com.fic.muei.lactachain.network.model.TransportChainDto
import com.fic.muei.lactachain.network.model.TransvaseChainDto
import com.fic.muei.lactachain.utils.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChainRepositoryImpl @Inject constructor(
    private var chainService: BlockchainService,
    private val farmChainMapper: Mapper<FarmChainDto, FarmParcialData>,
    private val transportChainMapper: Mapper<TransportChainDto, TransportParcialData>,
    private val transvaseChainMapper: Mapper<TransvaseChainDto, TransvaseData>,
    private val traceChainMapper: Mapper<TraceChainDto, TraceData>
) : ChainRepository {
    override fun getFarmAssetById(id: String) {
        //chainService.getFarmAssetById(id)
    }

    override fun queryAllFarms(): Flow<Result<List<FarmParcialData>>> {
        return flow {
            try {
                val farms = chainService
                    .getAllFarms()
                val result = Result.Success(farmChainMapper.mapFromDtoList(farms))
                emit(result)
            } catch (e: Exception) {
                emit(Result.Error(LactachainException(e.message)))
            }
        }
    }


}