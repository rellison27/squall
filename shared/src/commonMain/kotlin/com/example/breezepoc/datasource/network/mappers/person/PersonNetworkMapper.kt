package com.example.breezepoc.datasource.network.mappers.person

import com.example.breezepoc.datasource.network.model.PersonDto
import com.example.breezepoc.domain.model.Person.SinglePerson
import com.example.breezepoc.domain.model.util.DtoMapper

class PersonNetworkMapper constructor(
    private val singlePersonDetailsNetworkMapper: SinglePersonDetailsNetworkMapper
): DtoMapper<PersonDto, SinglePerson> {
    override fun mapToDomainModel(model: PersonDto?): SinglePerson {
        if(model != null) {
            return SinglePerson(
                id = model.id,
                personDetails = singlePersonDetailsNetworkMapper.mapToDomainModel(model.personDetails)
            )
        }
        return SinglePerson(null, null)
    }

    override fun mapFromDomainModel(domainModel: SinglePerson?): PersonDto {
        if(domainModel != null) {
            return PersonDto(
                id = domainModel.id,
                personDetails = singlePersonDetailsNetworkMapper.mapFromDomainModel(domainModel.personDetails)
            )
        }
        return PersonDto(null, null)
    }

}