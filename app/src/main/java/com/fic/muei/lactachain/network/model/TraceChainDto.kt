package com.fic.muei.lactachain.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class TraceChainDto(@Json(name = "id")val id: String,
                         @Json(name = "farms") val listFarms: List<FarmChainDto>,
                         @Json(name = "transvase")val listTransvase: List<TransvaseChainDto>
)