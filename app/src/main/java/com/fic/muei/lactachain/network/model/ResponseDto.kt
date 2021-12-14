package com.fic.muei.lactachain.network.model

data class ResponseDto<T>(val count:Int,
                       val results:List<T>
)