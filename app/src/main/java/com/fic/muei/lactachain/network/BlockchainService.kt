package com.fic.muei.lactachain.network

import com.fic.muei.lactachain.network.model.*
import retrofit2.http.*

interface BlockchainService {
    @GET("/api/traces/{id}")
    suspend fun getTraceById(@Path("id") id : String): TraceChainDto
}