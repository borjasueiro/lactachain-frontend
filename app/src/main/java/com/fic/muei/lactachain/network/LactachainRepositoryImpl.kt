package com.fic.muei.lactachain.network

import com.fic.muei.lactachain.model.FarmData
import com.fic.muei.lactachain.model.LactachainRepository
import com.fic.muei.lactachain.utils.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LactachainRepositoryImpl @Inject constructor(
    private val lactachainService : LactachainService,
    private val farmMapper: Mapper<FarmDto, FarmData>
):LactachainRepository {
    override fun getFarm(code: Int): Flow<FarmData> {
        return lactachainService.getFarmInfo(code).map{farmMapper.mapFromDto(it)}
    }
}