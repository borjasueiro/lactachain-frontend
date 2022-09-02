package com.fic.muei.lactachain.model

import kotlinx.coroutines.flow.Flow

interface ChainRepository {
    fun getFarmAssetById(id : String)
    fun queryAllFarms(): Flow<Result<List<FarmParcialData>>>
}