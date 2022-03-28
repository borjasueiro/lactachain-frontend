package com.fic.muei.lactachain.network


import com.fic.muei.lactachain.model.FinalSiloData
import com.fic.muei.lactachain.network.model.FinalSiloDto
import com.fic.muei.lactachain.utils.Mapper

class FinalSiloMapper: Mapper<FinalSiloDto, FinalSiloData> {
    override fun mapToDto(model: FinalSiloData): FinalSiloDto {
        return FinalSiloDto(model.code,model.type)
    }

    override fun mapFromDto(dto: FinalSiloDto): FinalSiloData {
        return FinalSiloData(dto.code,dto.type)
    }

    override fun mapFromDtoList(dtos: List<FinalSiloDto>): List<FinalSiloData> {
        return dtos.map{mapFromDto(it)}
    }

    override fun mapToDtoList(models: List<FinalSiloData>): List<FinalSiloDto> {
        return models.map{mapToDto(it)}
    }
}

