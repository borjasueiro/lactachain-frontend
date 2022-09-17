package com.fic.muei.lactachain.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class TransvaseChainDto(@Json(name ="src_silo_id")val siloSrc: String,
                             @Json(name="dst_silo_id")val siloDst: String,
                             @Json(name="temperature")val temperature: String,
                             @Json(name = "date") val date: String
)