package com.fic.muei.lactachain.network

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

interface LactachainService {
    @GET("farms/farm/{code}")
    fun getFarmInfo(@Path("code") code:Int): Flow<FarmDto>
}