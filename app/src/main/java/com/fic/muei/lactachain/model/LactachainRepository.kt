package com.fic.muei.lactachain.model

import kotlinx.coroutines.flow.Flow


interface LactachainRepository {

    fun getFarm(code:Int): Flow<Result<FarmData>>
    fun getTransporter(nif:String): Flow<Result<TransporterData?>>
    fun getTransportByTransporter(code:Int): Flow<Result<List<TransportListData>>>
    fun addTransport(transport:TransportData): Flow<Result<String>>
    fun addMilkCollection(milkCollection:MilkCollectionData): Flow<Result<Int>>
    fun setCredentials(user:String, password: String)
    fun getMilkCollections(): Flow<Result<List<MilkCollectionDataItem>>>
    fun addMilkDelivery(milkDelivery: MilkDeliveryData): Flow<Result<Int>>
    fun getReceptionSilosData(): Flow<Result<List<ReceptionSiloData>>>
    suspend fun updateMilkCollection(code: String): EmptyResult
    fun addFinalSilo(silo:FinalSiloData): Flow<Result<String>>
    fun addReceptionSilo():Flow<Result<Int>>
    fun getSilos(): Flow<Result<List<SiloDataItem>>>
    fun getFinalSilosData(): Flow<Result<List<FinalSiloData>>>

}

sealed class Result<out T: Any?> {
    data class Success<out T : Any?>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

sealed class EmptyResult {
    object Success : EmptyResult()
    data class Error(val exception: Exception) : EmptyResult()
}