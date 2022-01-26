package com.fic.muei.lactachain.network

import com.fic.muei.lactachain.model.MilkCollectionData
import com.fic.muei.lactachain.network.model.MilkCollectionDto
import com.fic.muei.lactachain.utils.Mapper

class MilkCollectionMapper: Mapper<MilkCollectionDto, MilkCollectionData> {
    override fun mapToDto(model: MilkCollectionData): MilkCollectionDto {
        return MilkCollectionDto(null,
            model.test,
            model.volumn,
            model.farm,
            null,
            model.transporter
        )
    }

    override fun mapFromDto(dto: MilkCollectionDto): MilkCollectionData {
        return MilkCollectionData(
            dto.code,
            dto.test,
            dto.volumn,
            dto.farm,
            dto.transporter
        )
    }

    override fun mapFromDtoList(dtos: List<MilkCollectionDto>): List<MilkCollectionData> {
        return dtos.map { mapFromDto(it) }
    }

    override fun mapToDtoList(models: List<MilkCollectionData>): List<MilkCollectionDto> {
        return models.map { mapToDto(it) }
    }
}