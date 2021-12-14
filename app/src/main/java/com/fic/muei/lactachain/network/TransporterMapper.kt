package com.fic.muei.lactachain.network

import com.fic.muei.lactachain.model.TransporterData
import com.fic.muei.lactachain.network.model.TransporterDto
import com.fic.muei.lactachain.utils.Mapper

class TransporterMapper: Mapper<TransporterDto, TransporterData> {
    override fun mapToDto(model: TransporterData): TransporterDto {
        return TransporterDto(model.code, model.name, model.nif)
    }

    override fun mapFromDto(dto: TransporterDto): TransporterData {
        return TransporterData(dto.code, dto.name, dto.nif)
    }
    override fun mapFromDtoList(dtos: List<TransporterDto>): List<TransporterData> {
        return dtos.map{mapFromDto(it)}
    }

    override fun mapToDtoList(models: List<TransporterData>): List<TransporterDto> {
        return models.map{mapToDto(it)}
    }
}
