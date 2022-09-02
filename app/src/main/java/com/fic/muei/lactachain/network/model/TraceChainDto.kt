package com.fic.muei.lactachain.network.model

data class TraceChainDto(val id: String,
                         val listFarms: List<FarmChainDto>,
                         val listTransvase: List<TransvaseChainDto>
)