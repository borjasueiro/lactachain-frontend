package com.fic.muei.lactachain.model


interface LactachainRepository {
    suspend fun getFarm(code:Int): FarmData

}