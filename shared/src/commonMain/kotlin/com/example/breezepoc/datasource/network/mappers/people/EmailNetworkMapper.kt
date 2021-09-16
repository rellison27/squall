package com.example.breezepoc.datasource.network.mappers.people

import com.example.breezepoc.datasource.cache.GetAllPeople
import com.example.breezepoc.datasource.cache.GetPersonById
import com.example.breezepoc.datasource.network.model.EmailDto
import com.example.breezepoc.domain.model.PeopleList.Email
import com.example.breezepoc.domain.model.util.DtoMapper
import io.ktor.http.*

class EmailNetworkMapper constructor() : DtoMapper<EmailDto, Email> {
    override fun mapToDomainModel(model: EmailDto?): Email {
        if(model != null) {
            return Email(
                address = model.address,
                private = model.private,
                exclude = model.exclude
            )
        }
        return Email(null, null, null)
    }

    override fun mapFromDomainModel(domainModel: Email?): EmailDto {
        if(domainModel != null) {
            return EmailDto(
                address = domainModel.address,
                private = domainModel.private,
                exclude = domainModel.exclude
            )
        }
        return EmailDto(null, null, null)
    }

    // TODO(
    //  Need to either create a table for email and add columns
    //  or add a column for the private and exclude values
    //  )
    fun mapFromEntity(entity: GetPersonById): Email {
            return Email(
                address = entity.email,
                private = null,
                exclude = null
            )
    }

    fun mapFromEntity(entity: GetAllPeople): Email {
        return Email(
            address = entity.email,
            private = null,
            exclude = null
        )
    }
}