package com.example.breezepoc.datasource.network.mappers.person

import com.example.breezepoc.datasource.cache.GetAllPeople
import com.example.breezepoc.datasource.cache.GetPersonById
import com.example.breezepoc.datasource.network.model.PhoneDto
import com.example.breezepoc.domain.model.PeopleList.Phone
import com.example.breezepoc.domain.model.util.DtoMapper

class PhoneNetworkMapper constructor(
    private val numberMapper: NumberNetworkMapper
): DtoMapper<PhoneDto, Phone> {
    override fun mapToDomainModel(model: PhoneDto?): Phone {
        if(model != null) {
            return Phone(
                home = numberMapper.mapToDomainModel(model.home),
                mobile = numberMapper.mapToDomainModel(model.mobile),
                work = numberMapper.mapToDomainModel(model.work)
            )
        }
        return Phone(null, null, null)
    }

    override fun mapFromDomainModel(domainModel: Phone?): PhoneDto {
        if(domainModel != null) {
            return PhoneDto(
                home = numberMapper.mapFromDomainModel(domainModel.home),
                mobile = numberMapper.mapFromDomainModel(domainModel.mobile),
                work = numberMapper.mapFromDomainModel(domainModel.work)
            )
        }
        return PhoneDto(null, null, null)
    }
    // TODO(We may want to change the overall domain structure of this overall if we don't use the api /people/:id)
    fun mapFromEntity(entity: GetPersonById?): Phone {
        if(entity != null) {
            return Phone(
                home = numberMapper.mapFromEntity(entity, "home"),
                mobile = numberMapper.mapFromEntity(entity, "mobile"),
                work = numberMapper.mapFromEntity(entity, "work")
            )
        }
        return Phone(null, null, null)
    }

    fun mapFromEntity(entity: GetAllPeople?): Phone {
        if(entity != null) {
            return Phone(
                home = numberMapper.mapFromEntity(entity, "home"),
                mobile = numberMapper.mapFromEntity(entity, "mobile"),
                work = numberMapper.mapFromEntity(entity, "work")
            )
        }
        return Phone(null, null, null)
    }
}