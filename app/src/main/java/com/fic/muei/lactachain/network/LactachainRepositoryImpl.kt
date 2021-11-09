package com.fic.muei.lactachain.network

import com.fic.muei.lactachain.model.FarmData
import com.fic.muei.lactachain.model.LactachainRepository
import javax.inject.Inject

class LactachainRepositoryImpl @Inject constructor(
    private val lactachainService : LactachainService,
    private val farmMapper: FarmMapper
):LactachainRepository {
    override suspend fun getFarm(code: Int): FarmData {
        val result = lactachainService.getFarmInfo(code)
        return farmMapper.mapFromDto(result)
    }
}