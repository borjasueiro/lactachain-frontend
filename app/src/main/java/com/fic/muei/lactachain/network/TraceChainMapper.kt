package com.fic.muei.lactachain.network

import com.fic.muei.lactachain.model.FarmParcialData
import com.fic.muei.lactachain.model.TraceData
import com.fic.muei.lactachain.model.TransvaseData
import com.fic.muei.lactachain.network.model.FarmChainDto
import com.fic.muei.lactachain.network.model.TraceChainDto
import com.fic.muei.lactachain.network.model.TransvaseChainDto
import com.fic.muei.lactachain.utils.Mapper

class TraceChainMapper: Mapper<TraceChainDto, TraceData> {
    override fun mapToDto(model: TraceData): TraceChainDto {
        return TraceChainDto(model.id,model.listFarms.map{ FarmChainDto(it.name, it.town, it.date,it.transportId, it.temperature) },
            model.listTransvase.map{TransvaseChainDto(it.siloSrc, it.siloDst, it.date)})
    }

    override fun mapFromDto(dto: TraceChainDto): TraceData {
        return TraceData(dto.id, dto.listFarms.map{ FarmParcialData(it.id, it.location, it.date,it.transportId, it.temperature) },
            dto.listTransvase.map{ TransvaseData(it.siloSrc, it.siloDst, it.date) })
    }

    override fun mapFromDtoList(dtos: List<TraceChainDto>): List<TraceData> {
        return dtos.map{mapFromDto(it)}
    }

    override fun mapToDtoList(models: List<TraceData>): List<TraceChainDto> {
        return models.map{mapToDto(it)}
    }
}
