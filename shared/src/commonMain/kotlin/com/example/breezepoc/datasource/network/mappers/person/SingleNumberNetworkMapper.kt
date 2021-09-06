package com.example.breezepoc.datasource.network.mappers.person

import com.example.breezepoc.datasource.cache.GetPersonById
import com.example.breezepoc.datasource.cache.People_Entity
import com.example.breezepoc.datasource.network.model.NumberDto
import com.example.breezepoc.datasource.network.model.PersonPhoneDto
import com.example.breezepoc.domain.model.Person.PhoneNumber
import com.example.breezepoc.domain.model.util.DtoMapper

class SingleNumberNetworkMapper constructor(): DtoMapper<NumberDto, PhoneNumber> {
    override fun mapToDomainModel(model: NumberDto?): PhoneNumber {
        if(model != null) {
            return PhoneNumber(
                number = model.number
            )
        }
        return PhoneNumber("")
    }

    override fun mapFromDomainModel(domainModel: PhoneNumber?): NumberDto {
        if (domainModel != null) {
            return NumberDto(
                number = domainModel.number
            )
        }
        return NumberDto("")
    }

// TODO(May need to re-evaluate this after making a decision in the SinglePhoneNetworkMapper)
    fun mapFromEntity(entity: GetPersonById?, type: String): PhoneNumber {
        var phoneNumber: String? = null
        when(type){
            "home" -> phoneNumber = entity?.home
            "mobile" -> phoneNumber = entity?.mobile
            "work" -> phoneNumber = entity?.work
        }
        if (entity != null) {
            return PhoneNumber(
                number = phoneNumber
            )
        }
        return PhoneNumber("")
    }
}