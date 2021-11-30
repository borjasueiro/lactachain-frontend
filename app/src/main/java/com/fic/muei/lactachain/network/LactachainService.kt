package com.fic.muei.lactachain.network

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface LactachainService {
    @GET("farms/farm/{code}")
    suspend fun getFarmInfo(@Path("code") code:Int): FarmDto
    @GET("transports/transporter/{code}")
    suspend fun getTransporterInfo(@Path("code") code:Int): TransporterDto
    @GET("transports/transport/{code}")
    suspend fun getTransportInfo(@Path("code") carRegistration:String): TransportDto
}