package com.example.breezepoc.domain.model.util

interface DtoMapper <T, DomainModel> {
    fun mapToDomainModel(model: T?): DomainModel

    fun mapFromDomainModel(domainModel: DomainModel?): T
}