package com.fic.muei.lactachain.network

import com.fic.muei.lactachain.model.TransportListData
import com.fic.muei.lactachain.network.model.TransportListDto
import com.fic.muei.lactachain.utils.Mapper

class TransportListMapper: Mapper<TransportListDto, TransportListData> {
    override fun mapToDto(model: TransportListData): TransportListDto {
        return TransportListDto(model.car_registration,
            model.current)
    }
    override fun mapFromDto(dto: TransportListDto): TransportListData {
        return TransportListData(dto.car_registration,
            dto.current)
    }
    override fun mapFromDtoList(dtos: List<TransportListDto>): List<TransportListData> {
        return dtos.map{mapFromDto(it)}
    }

    override fun mapToDtoList(models: List<TransportListData>): List<TransportListDto> {
        return models.map{mapToDto(it)}
    }
}