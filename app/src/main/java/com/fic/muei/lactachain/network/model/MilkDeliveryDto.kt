package com.fic.muei.lactachain.network.model

import java.util.*

data class MilkDeliveryDto(
    val code: Int?,
    val test: Boolean,
    val volumn: Int,
    val temperature: Int,
    val silo: Int,
    val date: String?,
    val transporter: Int
)