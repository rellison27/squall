package com.example.breezepoc.datasource.network.mappers.person

import com.example.breezepoc.datasource.cache.GetPersonById
import com.example.breezepoc.datasource.network.model.EmailDto
import com.example.breezepoc.domain.model.Person.Email
import com.example.breezepoc.domain.model.util.DtoMapper

class SingleEmailNetworkMapper constructor() : DtoMapper<EmailDto, Email> {
    override fun mapToDomainModel(model: EmailDto?): Email {
        if(model != null) {
            return Email(
                address = model.address
            )
        }
        return Email(null)
    }

    override fun mapFromDomainModel(domainModel: Email?): EmailDto {
        if(domainModel != null) {
            return EmailDto(
                address = domainModel.address,
                private = null,
                exclude = null
            )
        }
        return EmailDto(null, null, null)
    }

    // TODO(I'm pretty sure EmailNEtworkMapper is exactly the same besides the types)
    fun mapFromEntity(entity: GetPersonById): Email {
            return Email(
                address = entity.email
            )
    }
}