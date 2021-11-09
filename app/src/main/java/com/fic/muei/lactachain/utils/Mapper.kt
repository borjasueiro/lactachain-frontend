package com.fic.muei.lactachain.utils

interface Mapper<Dto,Model> {
    fun mapToDto(model:Model):Dto
    fun mapFromDto(dto:Dto):Model
    fun mapToDtoList(dtos:List<Dto>):List<Model>
    fun mapFromDtoList(models:List<Model>):List<Dto>
}