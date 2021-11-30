package com.fic.muei.lactachain.network

data class TransportDto(val carRegistration: String,
                         val tankCode: String,
                         val current: Boolean,
                         val capacity: Int,
                         val transporter: Int)