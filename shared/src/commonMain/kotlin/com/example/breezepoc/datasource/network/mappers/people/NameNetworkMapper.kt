package com.example.breezepoc.datasource.network.mappers.people

import com.example.breezepoc.datasource.cache.People_Entity
import com.example.breezepoc.datasource.network.model.NameDto
import com.example.breezepoc.domain.model.PeopleList.Name
import com.example.breezepoc.domain.model.util.DtoMapper

class NameNetworkMapper constructor() : DtoMapper<NameDto, Name> {

    override fun mapToDomainModel(model: NameDto?): Name {
        if (model != null) {
            return Name(
                first = model.first,
                last = model.last,
                middle = model.middle,
                maiden = model.maiden,
                nick = model.nick
            )
        }
        return Name(null, null, null, null, null)
    }

    override fun mapFromDomainModel(domainModel: Name?): NameDto {
        if (domainModel != null) {
            return NameDto(
                first = domainModel.first,
                last = domainModel.last,
                middle = domainModel.middle,
                maiden = domainModel.maiden,
                nick = domainModel.nick
            )
        }
        return NameDto(null, null, null, null, null)
    }

    fun mapFromEntity(first: String?, last: String?, middle: String?, maiden: String?, nick: String?): Name {
        return Name(
            first = first,
            last = last,
            middle = middle,
            maiden = maiden,
            nick = nick
        )
    }
}