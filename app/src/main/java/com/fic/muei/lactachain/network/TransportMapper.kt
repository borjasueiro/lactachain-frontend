package com.fic.muei.lactachain.network

import com.fic.muei.lactachain.model.TransportData
import com.fic.muei.lactachain.utils.Mapper

class TransportMapper: Mapper<TransportDto, TransportData> {
    override fun mapToDto(model: TransportData): TransportDto {
        return TransportDto(model.carRegistration, model.tankCode,
            model.current, model.capacity, model.transporter)
    }
    override fun mapFromDto(dto: TransportDto): TransportData {
        return TransportData(dto.carRegistration, dto.tankCode,
            dto.current, dto.capacity, dto.transporter)
    }

}