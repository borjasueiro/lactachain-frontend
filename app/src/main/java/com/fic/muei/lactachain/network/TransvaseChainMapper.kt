package com.fic.muei.lactachain.network

import com.fic.muei.lactachain.model.FarmParcialData
import com.fic.muei.lactachain.model.TransvaseData
import com.fic.muei.lactachain.network.model.FarmChainDto
import com.fic.muei.lactachain.network.model.TransvaseChainDto
import com.fic.muei.lactachain.utils.Mapper
class TransvaseChainMapper: Mapper<TransvaseChainDto, TransvaseData> {
    override fun mapToDto(model: TransvaseData): TransvaseChainDto {
        return TransvaseChainDto(model.siloSrc,model.siloDst, model.date)
    }

    override fun mapFromDto(dto: TransvaseChainDto): TransvaseData {
        return TransvaseData(dto.siloSrc, dto.SiloDst, dto.date)
    }

    override fun mapFromDtoList(dtos: List<TransvaseChainDto>): List<TransvaseData> {
        return dtos.map{mapFromDto(it)}
    }

    override fun mapToDtoList(models: List<TransvaseData>): List<TransvaseChainDto> {
        return models.map{mapToDto(it)}
    }
}

