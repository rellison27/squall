package com.example.breezepoc.datasource.network.mappers.people

import com.example.breezepoc.datasource.network.model.PeoplePhoneDto
import com.example.breezepoc.domain.model.Phone
import com.example.breezepoc.domain.model.util.DtoMapper

class PhoneNetworkMapper constructor() : DtoMapper<PeoplePhoneDto, Phone> {

    override fun mapToDomainModel(model: PeoplePhoneDto?): Phone {
        if (model != null) {
            return Phone(
                home = model.home,
                mobile = model.mobile,
                work = model.work,
            )
        }
        return Phone("", "", "")
    }

    override fun mapFromDomainModel(domainModel: Phone?): PeoplePhoneDto {
        if (domainModel != null) {
            return PeoplePhoneDto(
                home = domainModel.home,
                mobile = domainModel.mobile,
                work = domainModel.work
            )
        }
        return PeoplePhoneDto("", "", "")
    }
}