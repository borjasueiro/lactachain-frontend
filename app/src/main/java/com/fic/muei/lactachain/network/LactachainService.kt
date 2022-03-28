package com.fic.muei.lactachain.network

import com.fic.muei.lactachain.network.model.*
import retrofit2.http.*

interface LactachainService {
    @GET("farms/farm/{code}")
    suspend fun getFarmInfo(@Path("code") code:Int): FarmDto
    @GET("transports/transporter/{code}")
    suspend fun getTransporterInfoById(@Path("code") code:String): TransporterDto
    @GET("transports/transporter")
    suspend fun getTransporterInfo(@Query("nif") nif:String): ResponseDto<TransporterDto>
    @GET("transports/transport")
    suspend fun getTransportsByTransporter(@Query("transporter") code:Int): ResponseDto<TransportListDto>
    @GET("/farms/milkcollection/")
    suspend fun getMilkCollections(@Query("delivered") delivered:Boolean=false): List<MilkCollectionDto>
    @PUT("/farms/milkcollection/{code}/update_status/")
    suspend fun updateMilkCollection(@Path("code") code:String)
    @GET("/plant/receptionsilo/")
    suspend fun getReceptionSilos(): ResponseDto<ReceptionSiloDto>
    @GET("/plant/finalsilo/")
    suspend fun getFinalSilos(): ResponseDto<FinalSiloDto>
    @POST("/transports/transport/")
    suspend fun addTransport(@Body transport: TransportDto): ResponseDto<TransportDto>
    @POST("/farms/milkcollection/")
    suspend fun addMilkCollection(@Body milkCollection: MilkCollectionDto): MilkCollectionDto
    @POST("/transports/milkdelivery/")
    suspend fun addMilkDelivery(@Body milkDelivery: MilkDeliveryDto): MilkDeliveryDto
    @POST("/plant/finalsilo/")
    suspend fun addFinalSilo(@Body silo: FinalSiloDto): ResponseDto<FinalSiloDto>
    @POST("/plant/receptionsilo/")
    suspend fun addReceptionSilo(): ReceptionSiloDto
}