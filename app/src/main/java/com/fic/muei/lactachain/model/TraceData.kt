package com.fic.muei.lactachain.model

data class TraceData(val id: String,
                     val listFarms: List<FarmParcialData>,
                     val listTransvase: List<TransvaseData>
)