package com.example.breezepoc.datasource.network.mappers.people

import com.example.breezepoc.datasource.network.model.NameDto
import com.example.breezepoc.domain.model.Name
import com.example.breezepoc.domain.model.util.DtoMapper

class NameNetworkMapper constructor() : DtoMapper<NameDto, Name> {

    override fun mapToDomainModel(model: NameDto): Name {
        return Name(
            first = model.first,
            last = model.last,
            middle = model.middle,
            maiden = model.maiden,
            nick = model.nick
        )
    }

    override fun mapFromDomainModel(domainModel: Name): NameDto {
        return NameDto(
            first = domainModel.first,
            last = domainModel.last,
            middle = domainModel.middle,
            maiden = domainModel.maiden,
            nick = domainModel.nick
        )
    }
}