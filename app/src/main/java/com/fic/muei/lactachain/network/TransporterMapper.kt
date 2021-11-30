package com.fic.muei.lactachain.network

import com.fic.muei.lactachain.model.TransporterData
import com.fic.muei.lactachain.utils.Mapper

class TransporterMapper: Mapper<TransporterDto, TransporterData> {
    override fun mapToDto(model: TransporterData): TransporterDto{
        return TransporterDto(model.code, model.name, model.nif)
    }

    override fun mapFromDto(dto: TransporterDto): TransporterData {
        return TransporterData(dto.code, dto.name, dto.nif)
    }
}
