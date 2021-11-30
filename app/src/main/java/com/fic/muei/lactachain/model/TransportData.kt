package com.fic.muei.lactachain.model

data class TransportData(val carRegistration: String,
                         val tankCode: String,
                         val current: Boolean,
                         val capacity: Int,
                         val transporter: Int)