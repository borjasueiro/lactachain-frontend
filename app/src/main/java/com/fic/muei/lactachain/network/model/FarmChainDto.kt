package com.fic.muei.lactachain.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class FarmChainDto(@Json(name = "name")val id: String,
                        @Json(name = "location")val location: String,
                        @Json(name = "date")val date: String,
                        @Json(name = "transport_id") val transportId: String,
                        @Json(name = "temperature") val temperature: String
)