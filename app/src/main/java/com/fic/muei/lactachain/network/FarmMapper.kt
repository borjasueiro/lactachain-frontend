package com.fic.muei.lactachain.network

import com.fic.muei.lactachain.model.FarmData
import com.fic.muei.lactachain.network.model.FarmDto
import com.fic.muei.lactachain.utils.Mapper
class FarmMapper: Mapper<FarmDto, FarmData> {
    override fun mapToDto(model: FarmData): FarmDto {
        return FarmDto(model.code,model.province,model.town,model.name)
    }

    override fun mapFromDto(dto: FarmDto): FarmData {
        return FarmData(dto.code,dto.province,dto.town,dto.name)
    }

    override fun mapFromDtoList(dtos: List<FarmDto>): List<FarmData> {
        return dtos.map{mapFromDto(it)}
    }

    override fun mapToDtoList(models: List<FarmData>): List<FarmDto> {
        return models.map{mapToDto(it)}
    }
}
