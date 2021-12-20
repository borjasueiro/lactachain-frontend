package com.fic.muei.lactachain.network

import com.fic.muei.lactachain.model.TransportData
import com.fic.muei.lactachain.network.model.TransportDto
import com.fic.muei.lactachain.utils.Mapper

class TransportMapper: Mapper<TransportDto, TransportData> {
    override fun mapToDto(model: TransportData): TransportDto {
        return TransportDto(model.car_registration,model.tank_code,
            model.current, model.capacity, model.transporter)
    }
    override fun mapFromDto(dto: TransportDto): TransportData {
        return TransportData(dto.car_registration,dto.tank_code,
            dto.current, dto.capacity, dto.transporter)
    }
    override fun mapFromDtoList(dtos: List<TransportDto>): List<TransportData> {
        return dtos.map{mapFromDto(it)}
    }

    override fun mapToDtoList(models: List<TransportData>): List<TransportDto> {
        return models.map{mapToDto(it)}
    }
}