package com.fic.muei.lactachain.network

import com.fic.muei.lactachain.model.FarmData
import com.fic.muei.lactachain.model.LactachainRepository
import com.fic.muei.lactachain.utils.Mapper
import javax.inject.Inject

class LactachainRepositoryImpl @Inject constructor(
    private val lactachainService : LactachainService,
    private val farmMapper: Mapper<FarmDto, FarmData>
):LactachainRepository {
    override suspend fun getFarm(code: Int): FarmData {
        return lactachainService.getFarmInfo(code).let{farmMapper.mapFromDto(it)}
    }
}