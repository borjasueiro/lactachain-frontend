package com.fic.muei.lactachain.network

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface LactachainService {
    @GET("farms/farm/{code}")
    suspend fun getFarmInfo(@Path("code") code:Int): FarmDto
}