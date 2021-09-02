package com.example.breezepoc.datasource.network.mappers.person

import com.example.breezepoc.datasource.network.model.AddressDto
import com.example.breezepoc.domain.model.Person.Address
import com.example.breezepoc.domain.model.util.DtoMapper

class AddressNetworkMapper constructor(): DtoMapper<AddressDto, Address> {
    override fun mapToDomainModel(model: AddressDto?): Address {
        if (model != null) {
            return Address(
                StreetAddress = model.streetAddress,
                city = model.city,
                state = model.state,
                zip = model.zip
            )
        }
        return Address(null, null, null, null)
    }

    override fun mapFromDomainModel(domainModel: Address?): AddressDto {
        if (domainModel != null) {
            return AddressDto(
                streetAddress = domainModel.StreetAddress,
                city = domainModel.city,
                state = domainModel.state,
                zip = domainModel.zip
            )
        }
        return AddressDto(null, null, null, null)
    }

}