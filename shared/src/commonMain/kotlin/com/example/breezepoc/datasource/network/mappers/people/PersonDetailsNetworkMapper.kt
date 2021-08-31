package com.example.breezepoc.datasource.network.mappers.people

import com.example.breezepoc.datasource.network.model.NameDto
import com.example.breezepoc.datasource.network.model.PeopleDetailsDto
import com.example.breezepoc.datasource.network.model.PeoplePhoneDto
import com.example.breezepoc.datasource.network.model.PersonEmailDto
import com.example.breezepoc.domain.model.Name
import com.example.breezepoc.domain.model.PeopleDetails
import com.example.breezepoc.domain.model.Phone
import com.example.breezepoc.domain.model.util.DtoMapper

class PersonDetailsNetworkMapper constructor(
    private val nameMapper: NameNetworkMapper,
    private val phoneMapper: PhoneNetworkMapper
) : DtoMapper<PeopleDetailsDto, PeopleDetails> {


    override fun mapToDomainModel(model: PeopleDetailsDto?): PeopleDetails {
        if (model != null) {
            return PeopleDetails(
                name = nameMapper.mapToDomainModel(model?.name),
                phone = phoneMapper.mapToDomainModel(model?.phone),
                email = model.email,
                archived = model.archived,
                profilePicture = model.profilePicture
            )
        }
        return PeopleDetails(Name("","","","", ""), Phone("","",""), "", 0, "")
    }

    override fun mapFromDomainModel(domainModel: PeopleDetails?): PeopleDetailsDto {
        if (domainModel != null) {
            return PeopleDetailsDto(
                name = nameMapper.mapFromDomainModel(domainModel.name),
                phone = phoneMapper.mapFromDomainModel(domainModel.phone),
                email = domainModel.email,
                archived = domainModel.archived,
                profilePicture = domainModel.profilePicture
            )
        }
        return PeopleDetailsDto(NameDto("", ""), PeoplePhoneDto("", "", ""), "", 0, "")
    }
}