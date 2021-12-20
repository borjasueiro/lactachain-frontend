package com.fic.muei.lactachain.model

import kotlinx.coroutines.flow.Flow


interface LactachainRepository {

    fun getFarm(code:Int): Flow<Result<FarmData>>
    fun getTransporter(nif:String): Flow<Result<TransporterData?>>
    fun getTransportByTransporter(code:Int): Flow<Result<List<TransportListData>>>
    fun addTransport(transport:TransportData): Flow<Result<String>>
    fun setCredentials(user:String, password: String)

}

sealed class Result<out T: Any?> {
    data class Success<out T : Any?>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}