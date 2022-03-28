package com.fic.muei.lactachain.network


import com.fic.muei.lactachain.model.FinalSiloData
import com.fic.muei.lactachain.model.SiloDataItem
import com.fic.muei.lactachain.network.model.FinalSiloDto
import com.fic.muei.lactachain.network.model.ReceptionSiloDto
import com.fic.muei.lactachain.utils.Mapper

class SiloReceptionDataMapper: Mapper<ReceptionSiloDto, SiloDataItem> {
    override fun mapToDto(model: SiloDataItem): ReceptionSiloDto {
        return ReceptionSiloDto(model.code!!)
    }

    override fun mapFromDto(dto: ReceptionSiloDto): SiloDataItem {
        return SiloDataItem(dto.code,"3")
    }

    override fun mapFromDtoList(dtos: List<ReceptionSiloDto>): List<SiloDataItem> {
        return dtos.map{mapFromDto(it)}
    }

    override fun mapToDtoList(models: List<SiloDataItem>): List<ReceptionSiloDto> {
        return models.map{mapToDto(it)}
    }
}

