package com.example.breezepoc.datasource.network.mappers.people

import com.example.breezepoc.datasource.network.model.PeopleDto
import com.example.breezepoc.domain.model.Person
import com.example.breezepoc.domain.model.util.DtoMapper

class PeopleListNetworkMapper constructor(
    private val personDetailsNetworkMapper: PersonDetailsNetworkMapper
): DtoMapper<PeopleDto, Person> {

    override fun mapToDomainModel(model: PeopleDto?): Person {
        if (model != null) {
            return Person(
                id = model.id,
                personDetails = personDetailsNetworkMapper.mapToDomainModel(model.personDetails)
            )
        }
        return Person(null, null)
    }

    override fun mapFromDomainModel(domainModel: Person?): PeopleDto {
        if (domainModel != null) {
            return PeopleDto(
                id = domainModel.id,
                personDetails = personDetailsNetworkMapper.mapFromDomainModel(domainModel.personDetails)
            )
        }
        return PeopleDto(null, null)
    }

    fun mapFromDomainList(initial: List<PeopleDto>): List<Person> {
        return initial.map { mapToDomainModel(it) }
    }
}