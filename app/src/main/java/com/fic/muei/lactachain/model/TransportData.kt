package com.fic.muei.lactachain.model

data class TransportData(val car_registration: String,
                          val tank_code: String,
                          val current: Boolean,
                          val capacity: Int,
                          val transporter: Int)
