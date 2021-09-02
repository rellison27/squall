package com.example.breezepoc.datasource.network.mappers.person

import com.example.breezepoc.datasource.network.mappers.people.NameNetworkMapper
import com.example.breezepoc.datasource.network.model.PersonDetailsDto
import com.example.breezepoc.domain.model.Person.SinglePersonDetails
import com.example.breezepoc.domain.model.util.DtoMapper

class SinglePersonDetailsNetworkMapper constructor(
    private val nameMapper: NameNetworkMapper,
    private val addressMapper: AddressNetworkMapper,
    private val phoneMapper: SinglePhoneNetworkMapper,
    private val emailMapper: SingleEmailNetworkMapper
): DtoMapper<PersonDetailsDto, SinglePersonDetails> {
    override fun mapToDomainModel(model: PersonDetailsDto?): SinglePersonDetails {
        if(model != null){
            return SinglePersonDetails(
                name = nameMapper.mapToDomainModel(model.name),
                address = addressMapper.mapToDomainModel(model.address),
                phone = phoneMapper.mapToDomainModel(model.phone),
                profilePicture = model.profilePicture,
                birthdate = model.birthdate,
                email = emailMapper.mapToDomainModel(model.email),
                archived = model.archived
            )
        }
        return SinglePersonDetails(null, null, null, null, null, null, null)
    }

    override fun mapFromDomainModel(domainModel: SinglePersonDetails?): PersonDetailsDto {
        if(domainModel != null) {
            return PersonDetailsDto(
                name = nameMapper.mapFromDomainModel(domainModel.name),
                address = addressMapper.mapFromDomainModel(domainModel.address),
                phone = phoneMapper.mapFromDomainModel(domainModel.phone),
                profilePicture = domainModel.profilePicture,
                birthdate = domainModel.birthdate,
                email = emailMapper.mapFromDomainModel(domainModel.email),
                archived = domainModel.archived
            )
        }
        return PersonDetailsDto(null, null, null, null, null, null, null)
    }

}