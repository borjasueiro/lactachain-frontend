package com.fic.muei.lactachain.utils

interface Mapper<Dto,Model> {
    fun mapToDto(model:Model):Dto
    fun mapFromDto(dto:Dto):Model
    fun mapToDtoList(models:List<Model>):List<Dto>
    fun mapFromDtoList(dtos:List<Dto>):List<Model>
}