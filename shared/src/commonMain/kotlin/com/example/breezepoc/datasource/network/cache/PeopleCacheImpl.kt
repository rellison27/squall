package com.example.breezepoc.datasource.network.cache

import com.example.breezepoc.datasource.cache.PeopleDatabase
import com.example.breezepoc.datasource.cache.PeopleDbQueries
import com.example.breezepoc.datasource.network.mappers.people.NameNetworkMapper
import com.example.breezepoc.datasource.network.mappers.people.PeopleNetworkMapper
import com.example.breezepoc.datasource.network.mappers.people.PersonDetailsNetworkMapper
import com.example.breezepoc.datasource.network.mappers.people.PhoneNetworkMapper
import com.example.breezepoc.datasource.network.mappers.person.*
import com.example.breezepoc.domain.model.PeopleList.Person
import com.example.breezepoc.domain.model.Person.SinglePerson

class PeopleCacheImpl(
    val peopleDatabase: PeopleDatabase
): PeopleCache {

    // located in shared build/generated/sqldelight folder
    // you can also see the functions in the PeopleDb.sq
    // sqldelight generates the classes from that file
    private var queries: PeopleDbQueries = peopleDatabase.peopleDbQueries

    // for People
    val nameNetworkMapper: NameNetworkMapper = NameNetworkMapper()
    val phoneNetworkMapper: PhoneNetworkMapper = PhoneNetworkMapper()
    val personDetailsNetworkMapper: PersonDetailsNetworkMapper = PersonDetailsNetworkMapper(
        nameNetworkMapper, phoneNetworkMapper
    )
    val peopleNetworkMapper: PeopleNetworkMapper = PeopleNetworkMapper(
        personDetailsNetworkMapper
    )

    // for Single Person call
    val singleNumberNetworkMapper: SingleNumberNetworkMapper = SingleNumberNetworkMapper()
    val emailNetworkMapper: SingleEmailNetworkMapper = SingleEmailNetworkMapper()
    val singlePhoneNetworkMapper: SinglePhoneNetworkMapper = SinglePhoneNetworkMapper(singleNumberNetworkMapper)
    val addressNetworkMapper: AddressNetworkMapper = AddressNetworkMapper()
    val singlePersonDetailsNetworkMapper: SinglePersonDetailsNetworkMapper = SinglePersonDetailsNetworkMapper(
        nameNetworkMapper,
        addressNetworkMapper,
        singlePhoneNetworkMapper,
        emailNetworkMapper
    )
    val personNetworkMapper: PersonNetworkMapper = PersonNetworkMapper(singlePersonDetailsNetworkMapper)

    override fun insert(person: SinglePerson) {
        queries.insertPerson(
            id = person.id,
            first_name = person.personDetails?.name?.first,
            last_name = person.personDetails?.name?.last,
            nick_name = person.personDetails?.name?.nick,
            maiden_name = person.personDetails?.name?.maiden,
            middle_name = person.personDetails?.name?.middle,
            profile_picture = person.personDetails?.profilePicture,
            archived = person.personDetails?.archived,
            email = person.personDetails?.email?.address
        )
    }

    override fun insert(people: List<SinglePerson>) {
        for (person in people){
            insert(person)
        }
    }

    override fun getAll(): List<Person> {
        val people = queries.getAllPeople().executeAsList()
        val mapped = peopleNetworkMapper.mapFromEntityList(people)
        return mapped
    }

    override fun get(personId: Int): SinglePerson? {
        return try {
            val person = queries.getPersonById(id = personId.toLong()).executeAsOne()
            val mapped = personNetworkMapper.mapFromEntity(person)
            return mapped
        }catch (e: NullPointerException){
            null
        }
    }


}