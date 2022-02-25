package com.example.breezepoc.datasource.network.mappers.people

import com.example.breezepoc.datasource.cache.GetAllPeople
import com.example.breezepoc.datasource.network.mappers.person.AddressNetworkMapper
import com.example.breezepoc.datasource.network.mappers.person.PhoneNetworkMapper
import com.example.breezepoc.datasource.network.model.PeopleDto
import com.example.breezepoc.domain.model.PeopleList.Person
import com.example.breezepoc.domain.model.util.DtoMapper

class PeopleNetworkMapper constructor(
    private val nameNetworkMapper: NameNetworkMapper,
    private val phoneNetworkMapper: PhoneNetworkMapper,
    private val addressNetworkMapper: AddressNetworkMapper,
    private val emailNetworkMapper: EmailNetworkMapper
): DtoMapper<PeopleDto, Person> {

    override fun mapToDomainModel(model: PeopleDto?): Person {
        if (model != null) {
            return Person(
                id = model.id,
                archived = model.archived,
                birthdate = model.birthdate,
                address = addressNetworkMapper.mapToDomainModel(model.address),
                email = emailNetworkMapper.mapToDomainModel(model.email),
                phone = phoneNetworkMapper.mapToDomainModel(model.phone),
                name = nameNetworkMapper.mapToDomainModel(model.name),
                profilePicture = model.profilePicture,
                familyRole = model.familyRole
            )
        }
        // TODO(Probably don't need wrapped if)
        return Person(0, false, null, null, null, null, null, null, familyRole = null)
    }

    override fun mapFromDomainModel(domainModel: Person?): PeopleDto {
        if (domainModel != null) {
            return PeopleDto(
                id = domainModel.id,
                archived = domainModel.archived,
                birthdate = domainModel.birthdate,
                address = addressNetworkMapper.mapFromDomainModel(domainModel.address),
                email = emailNetworkMapper.mapFromDomainModel(domainModel.email),
                phone = phoneNetworkMapper.mapFromDomainModel(domainModel.phone),
                name = nameNetworkMapper.mapFromDomainModel(domainModel.name),
                profilePicture = domainModel.profilePicture,
                familyRole = domainModel.familyRole
            )
        }
        // TODO(Probably don't need wrapped if)
        return PeopleDto(0, false, null, null, null, null, null, null, familyRole = null)
    }

    fun mapToDomainList(initial: List<PeopleDto>): List<Person> {
        return initial.map { mapToDomainModel(it) }
    }

    fun mapFromEntity(entity: GetAllPeople): Person {
            return Person(
                id = entity.id,
                archived = entity.archived,
                birthdate = entity.birthdate,
                address = addressNetworkMapper.mapFromEntity(entity),
                email = emailNetworkMapper.mapFromEntity(entity),
                phone = phoneNetworkMapper.mapFromEntity(entity),
                name = nameNetworkMapper.mapFromEntity(entity.first_name, entity.last_name, entity.middle_name, entity.maiden_name, entity.nick_name),
                profilePicture = entity.profile_picture,
                familyRole = entity.family_role
            )
    }

    fun mapFromEntityList(initial: List<GetAllPeople>): List<Person> {
        return initial.map { mapFromEntity(it) }
    }
}