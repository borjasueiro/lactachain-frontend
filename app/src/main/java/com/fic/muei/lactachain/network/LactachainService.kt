package com.fic.muei.lactachain.network

import com.fic.muei.lactachain.network.model.*
import retrofit2.http.*

interface LactachainService {
    @GET("farms/farm/{code}")
    suspend fun getFarmInfo(@Path("code") code:Int): FarmDto
    @GET("transports/transporter")
    suspend fun getTransporterInfo(@Query("nif") nif:String): ResponseDto<TransporterDto>
    @GET("transports/transport")
    suspend fun getTransportsByTransporter(@Query("transporter") code:Int): ResponseDto<TransportListDto>
    @POST("/transports/transport/")
    suspend fun addTransport(@Body transport: TransportDto): ResponseDto<TransportDto>
    @POST("/farms/milkcollection/")
    suspend fun addMilkCollection(@Body milkCollection: MilkCollectionDto): MilkCollectionDto

}