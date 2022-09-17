package com.fic.muei.lactachain.network

import android.util.Log
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

    override fun getTraceById(id: String): Flow<Result<TraceData>> {
        return flow{
            try{
                val trace = chainService.getTraceById(id)
                val result = Result.Success(traceChainMapper.mapFromDto(trace))
                emit(result)
            }catch (e: Exception) {
                Log.i("test","${e.message}")
                emit(Result.Error(LactachainException(e.message)))
            }
        }
    }

}