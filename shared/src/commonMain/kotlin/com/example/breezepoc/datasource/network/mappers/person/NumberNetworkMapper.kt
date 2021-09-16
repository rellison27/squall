package com.example.breezepoc.datasource.network.mappers.person

import com.example.breezepoc.datasource.cache.GetAllPeople
import com.example.breezepoc.datasource.cache.GetPersonById
import com.example.breezepoc.datasource.network.model.NumberDto
import com.example.breezepoc.domain.model.PeopleList.PhoneNumber
import com.example.breezepoc.domain.model.util.DtoMapper

class NumberNetworkMapper : DtoMapper<NumberDto, PhoneNumber> {
    override fun mapToDomainModel(model: NumberDto?): PhoneNumber {
        if(model != null) {
            return PhoneNumber(
                number = model.number,
                private = model.private
            )
        }
        return PhoneNumber("", null)
    }

    override fun mapFromDomainModel(domainModel: PhoneNumber?): NumberDto {
        if (domainModel != null) {
            return NumberDto(
                number = domainModel.number,
                private = domainModel.private
            )
        }
        return NumberDto("", null)
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
                number = phoneNumber,
                // TODO(need to add private value to table)
                private = null
            )
        }
        return PhoneNumber("", null)
    }

    fun mapFromEntity(entity: GetAllPeople?, type: String): PhoneNumber {
        var phoneNumber: String? = null
        when(type){
            "home" -> phoneNumber = entity?.home
            "mobile" -> phoneNumber = entity?.mobile
            "work" -> phoneNumber = entity?.work
        }
        if (entity != null) {
            return PhoneNumber(
                number = phoneNumber,
                // TODO(need to add private value to table)
                private = null
            )
        }
        return PhoneNumber("", null)
    }
}