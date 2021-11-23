package com.fic.muei.lactachain.model

import kotlinx.coroutines.flow.Flow


interface LactachainRepository {

    fun getFarm(code:Int): Flow<FarmData>

}