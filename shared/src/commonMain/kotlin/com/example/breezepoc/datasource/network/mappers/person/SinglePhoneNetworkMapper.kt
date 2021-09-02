package com.example.breezepoc.datasource.network.mappers.person

import com.example.breezepoc.datasource.network.model.NumberDto
import com.example.breezepoc.datasource.network.model.PersonPhoneDto
import com.example.breezepoc.domain.model.PeopleList.Phone
import com.example.breezepoc.domain.model.Person.PhoneNumber
import com.example.breezepoc.domain.model.Person.SinglePhone
import com.example.breezepoc.domain.model.util.DtoMapper

class SinglePhoneNetworkMapper constructor(
    private val singleNumberMapper: SingleNumberNetworkMapper
): DtoMapper<PersonPhoneDto, SinglePhone> {
    override fun mapToDomainModel(model: PersonPhoneDto?): SinglePhone {
        if(model != null) {
            return SinglePhone(
                home = singleNumberMapper.mapToDomainModel(model.home),
                mobile = singleNumberMapper.mapToDomainModel(model.mobile),
                work = singleNumberMapper.mapToDomainModel(model.work)
            )
        }
        return SinglePhone(null, null, null)
    }

    override fun mapFromDomainModel(domainModel: SinglePhone?): PersonPhoneDto {
        if(domainModel != null) {
            return PersonPhoneDto(
                home = singleNumberMapper.mapFromDomainModel(domainModel.home),
                mobile = singleNumberMapper.mapFromDomainModel(domainModel.mobile),
                work = singleNumberMapper.mapFromDomainModel(domainModel.work)
            )
        }
        return PersonPhoneDto(null, null, null)
    }


}