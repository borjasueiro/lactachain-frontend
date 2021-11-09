package com.fic.muei.lactachain.network

import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

interface LactachainService {
    @GET("farms/farm/{code}")
    suspend fun getFarmInfo(@Path("code") code:Int):FarmDto
}