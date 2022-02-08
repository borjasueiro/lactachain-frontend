package com.fic.muei.lactachain.network

import com.fic.muei.lactachain.model.MilkDeliveryData
import com.fic.muei.lactachain.network.model.MilkDeliveryDto
import com.fic.muei.lactachain.utils.Mapper

class MilkDeliveryMapper: Mapper<MilkDeliveryDto, MilkDeliveryData> {
    override fun mapToDto(model: MilkDeliveryData): MilkDeliveryDto {
        return MilkDeliveryDto(null,
            model.test,
            model.volumn,
            model.temperature,
            model.silo,
            null,
            model.transporter
        )
    }

    override fun mapFromDto(dto: MilkDeliveryDto): MilkDeliveryData {
        return MilkDeliveryData(
            dto.code,
            dto.test,
            dto.volumn,
            dto.temperature,
            dto.silo,
            dto.transporter
        )
    }

    override fun mapFromDtoList(dtos: List<MilkDeliveryDto>): List<MilkDeliveryData> {
        return dtos.map { mapFromDto(it) }
    }

    override fun mapToDtoList(models: List<MilkDeliveryData>): List<MilkDeliveryDto> {
        return models.map { mapToDto(it) }
    }
}