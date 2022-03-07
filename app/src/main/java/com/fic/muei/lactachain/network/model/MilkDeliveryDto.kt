package com.fic.muei.lactachain.network.model

data class MilkDeliveryDto(
    val code: Int?,
    val test: Boolean,
    val volumn: Int,
    val temperature: Int,
    val reception_silo: Int,
    val date: String?,
    val transporter: Int
)