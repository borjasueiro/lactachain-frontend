package com.fic.muei.lactachain.network.model



data class TransportDto( val car_registration: String,
                         val tank_code: String,
                         val current: Boolean,
                         val capacity: Int,
                         val transporter: Int)