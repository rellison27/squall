package com.example.breezepoc.datasource.network.mappers.person

import com.example.breezepoc.datasource.cache.GetPersonById
import com.example.breezepoc.datasource.cache.People_Entity
import com.example.breezepoc.datasource.network.model.PersonEmailDto
import com.example.breezepoc.domain.model.Person.Email
import com.example.breezepoc.domain.model.util.DtoMapper

class SingleEmailNetworkMapper constructor() : DtoMapper<PersonEmailDto, Email> {
    override fun mapToDomainModel(model: PersonEmailDto?): Email {
        if(model != null) {
            return Email(
                address = model.address
            )
        }
        return Email(null)
    }

    override fun mapFromDomainModel(domainModel: Email?): PersonEmailDto {
        if(domainModel != null) {
            return PersonEmailDto(
                address = domainModel.address
            )
        }
        return PersonEmailDto(null)
    }

    fun mapFromEntity(entity: GetPersonById): Email {
        if(entity != null) {
            return Email(
                address = entity.email
            )
        }
        return Email(null)
    }
}