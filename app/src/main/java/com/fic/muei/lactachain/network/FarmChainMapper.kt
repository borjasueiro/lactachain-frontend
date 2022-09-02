package com.fic.muei.lactachain.network

import com.fic.muei.lactachain.model.FarmParcialData
import com.fic.muei.lactachain.network.model.FarmChainDto
import com.fic.muei.lactachain.utils.Mapper

class FarmChainMapper: Mapper<FarmChainDto, FarmParcialData> {
    override fun mapToDto(model: FarmParcialData): FarmChainDto {
        return FarmChainDto(model.name,model.town, model.date, model.transportId, model.temperature)
    }

    override fun mapFromDto(dto: FarmChainDto): FarmParcialData {
        return FarmParcialData(dto.id,dto.location, dto.date, dto.transportId, dto.temperature)
    }

    override fun mapFromDtoList(dtos: List<FarmChainDto>): List<FarmParcialData> {
        return dtos.map{mapFromDto(it)}
    }

    override fun mapToDtoList(models: List<FarmParcialData>): List<FarmChainDto> {
        return models.map{mapToDto(it)}
    }
}
