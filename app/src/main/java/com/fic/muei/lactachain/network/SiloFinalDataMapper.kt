package com.fic.muei.lactachain.network


import com.fic.muei.lactachain.model.FinalSiloData
import com.fic.muei.lactachain.model.SiloDataItem
import com.fic.muei.lactachain.network.model.FinalSiloDto
import com.fic.muei.lactachain.utils.Mapper

class SiloFinalDataMapper: Mapper<FinalSiloDto, SiloDataItem> {
    override fun mapToDto(model: SiloDataItem): FinalSiloDto {
        return FinalSiloDto(model.code,model.type)
    }

    override fun mapFromDto(dto: FinalSiloDto): SiloDataItem {
        return SiloDataItem(dto.code,dto.type)
    }

    override fun mapFromDtoList(dtos: List<FinalSiloDto>): List<SiloDataItem> {
        return dtos.map{mapFromDto(it)}
    }

    override fun mapToDtoList(models: List<SiloDataItem>): List<FinalSiloDto> {
        return models.map{mapToDto(it)}
    }
}

