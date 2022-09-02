package com.fic.muei.lactachain.network

import com.fic.muei.lactachain.model.FarmParcialData
import com.fic.muei.lactachain.model.TransportParcialData
import com.fic.muei.lactachain.network.model.FarmChainDto
import com.fic.muei.lactachain.network.model.TransportChainDto
import com.fic.muei.lactachain.utils.Mapper

class TransportChainMapper: Mapper<TransportChainDto, TransportParcialData> {
    override fun mapToDto(model: TransportParcialData): TransportChainDto {
        return TransportChainDto(model.transportId,model.SiloId, model.date)
    }

    override fun mapFromDto(dto:TransportChainDto): TransportParcialData {
        return TransportParcialData(dto.id,dto.siloId, dto.date)
    }

    override fun mapFromDtoList(dtos: List<TransportChainDto>): List<TransportParcialData> {
        return dtos.map{mapFromDto(it)}
    }

    override fun mapToDtoList(models: List<TransportParcialData>): List<TransportChainDto> {
        return models.map{mapToDto(it)}
    }
}
