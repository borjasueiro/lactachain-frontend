package com.fic.muei.lactachain.network

import com.fic.muei.lactachain.network.model.FarmDto
import com.fic.muei.lactachain.network.model.ResponseDto
import com.fic.muei.lactachain.network.model.TransportDto
import com.fic.muei.lactachain.network.model.TransporterDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LactachainService {
    @GET("farms/farm/{code}")
    suspend fun getFarmInfo(@Path("code") code:Int): FarmDto
    @GET("transports/transporter")
    suspend fun getTransporterInfo(@Query("nif") nif:String): ResponseDto
    @GET("transports/transport/{code}")
    suspend fun getTransportInfo(@Path("code") carRegistration:String): TransportDto
}