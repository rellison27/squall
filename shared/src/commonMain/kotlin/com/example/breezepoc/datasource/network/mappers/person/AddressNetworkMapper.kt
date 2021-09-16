package com.example.breezepoc.datasource.network.mappers.person

import com.example.breezepoc.datasource.cache.GetAllPeople
import com.example.breezepoc.datasource.cache.GetPersonById
import com.example.breezepoc.datasource.network.model.AddressDto
import com.example.breezepoc.domain.model.PeopleList.Address
import com.example.breezepoc.domain.model.util.DtoMapper

class AddressNetworkMapper constructor(): DtoMapper<AddressDto, Address> {
    override fun mapToDomainModel(model: AddressDto?): Address {
        if (model != null) {
            return Address(
                street = model.street,
                city = model.city,
                state = model.state,
                zip = model.zip,
                longitude = model.longitude,
                latitude = model.latitude,
                private = model.private
            )
        }
        return Address(null, null, null, null, null, null, null)
    }

    override fun mapFromDomainModel(domainModel: Address?): AddressDto {
        if (domainModel != null) {
            return AddressDto(
                street = domainModel.street,
                city = domainModel.city,
                state = domainModel.state,
                zip = domainModel.zip,
                latitude = domainModel.latitude,
                longitude = domainModel.longitude,
                private = domainModel.private
            )
        }
        return AddressDto(null, null, null, null, null, null, null)
    }

    // Testing this out TODO(May need to rethink this but kotlin allows same funciton with different types )
     fun mapFromEntity(entity: GetPersonById?): Address {
            return Address(
                street = entity?.street,
                city = entity?.city,
                state = entity?.state,
                zip = entity?.zip,
                longitude = entity?.longitude,
                latitude = entity?.latitude,
                private = entity?.private_
            )
    }

    fun mapFromEntity(entity: GetAllPeople?): Address {
        return Address(
            street = entity?.street,
            city = entity?.city,
            state = entity?.state,
            zip = entity?.zip,
            longitude = entity?.longitude,
            latitude = entity?.latitude,
            private = entity?.private_
        )
    }
}