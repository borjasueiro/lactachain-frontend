package com.fic.muei.lactachain.network.model

import java.util.*

data class MilkCollectionDto(
    val code: Int?,
    val test: Boolean,
    val volumn: Int,
    val farm: Int,
    val date: String?,
    val transporter: Int
)