package com.fic.muei.lactachain.network

import com.fic.muei.lactachain.model.FarmData
import com.fic.muei.lactachain.utils.Mapper
import javax.inject.Singleton

class FarmMapper: Mapper<FarmDto, FarmData> {
    override fun mapToDto(model: FarmData): FarmDto {
        return FarmDto(model.code,model.province,model.town,model.name)
    }

    override fun mapFromDto(dto: FarmDto): FarmData {
        return FarmData(dto.code,dto.province,dto.town,dto.name)
    }

/*    override fun mapToDtoList(dtos: List<FarmDto>): List<FarmData> {
        return dtos.map{mapFromDto(it)}
    }

    override fun mapFromDtoList(models: List<FarmData>): List<FarmDto> {
        return models.map{mapToDto(it)}
    }*/
}
