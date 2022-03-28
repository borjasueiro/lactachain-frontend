package com.fic.muei.lactachain.network


import com.fic.muei.lactachain.model.ReceptionSiloData
import com.fic.muei.lactachain.network.model.ReceptionSiloDto
import com.fic.muei.lactachain.utils.Mapper

class ReceptionSiloMapper: Mapper<ReceptionSiloDto, ReceptionSiloData> {
    override fun mapToDto(model: ReceptionSiloData): ReceptionSiloDto {
        return ReceptionSiloDto(model.code)
    }

    override fun mapFromDto(dtoReception: ReceptionSiloDto): ReceptionSiloData {
        return ReceptionSiloData(dtoReception.code)
    }

    override fun mapFromDtoList(dtoReceptions: List<ReceptionSiloDto>): List<ReceptionSiloData> {
        return dtoReceptions.map{mapFromDto(it)}
    }

    override fun mapToDtoList(models: List<ReceptionSiloData>): List<ReceptionSiloDto> {
        return models.map{mapToDto(it)}
    }
}
