package com.example.breezepoc.datasource.network.mappers.people

import com.example.breezepoc.datasource.cache.GetAllPeople
import com.example.breezepoc.datasource.cache.GetPersonById
import com.example.breezepoc.datasource.cache.People_Entity
import com.example.breezepoc.datasource.network.model.PeopleDto
import com.example.breezepoc.domain.model.PeopleList.Person
import com.example.breezepoc.domain.model.util.DtoMapper

class PeopleNetworkMapper constructor(
    private val personDetailsNetworkMapper: PersonDetailsNetworkMapper
): DtoMapper<PeopleDto, Person> {

    override fun mapToDomainModel(model: PeopleDto?): Person {
        if (model != null) {
            return Person(
                id = model.id,
                personDetails = personDetailsNetworkMapper.mapToDomainModel(model.personDetails)
            )
        }
        // TODO(Probably don't need wrapped if)
        return Person(0, null)
    }

    override fun mapFromDomainModel(domainModel: Person?): PeopleDto {
        if (domainModel != null) {
            return PeopleDto(
                id = domainModel.id,
                personDetails = personDetailsNetworkMapper.mapFromDomainModel(domainModel.personDetails)
            )
        }
        // TODO(Probably don't need wrapped if)
        return PeopleDto(0, null)
    }

    fun mapToDomainList(initial: List<PeopleDto>): List<Person> {
        return initial.map { mapToDomainModel(it) }
    }

    fun mapFromEntity(entity: GetAllPeople): Person {
        if (entity != null) {
            return Person(
                id = entity.id,
                personDetails = personDetailsNetworkMapper.mapFromEntity(entity)
            )
        }
        // TODO(Probably don't need wrapped if)
        return Person(0, null)
    }

    fun mapFromEntityList(initial: List<GetAllPeople>): List<Person> {
        return initial.map { mapFromEntity(it) }
    }
}