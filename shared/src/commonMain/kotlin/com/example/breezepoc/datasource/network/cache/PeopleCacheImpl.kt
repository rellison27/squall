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
    private val nameNetworkMapper: NameNetworkMapper = NameNetworkMapper()
    private val phoneNetworkMapper: PhoneNetworkMapper = PhoneNetworkMapper()
    private val personDetailsNetworkMapper: PersonDetailsNetworkMapper = PersonDetailsNetworkMapper(
        nameNetworkMapper, phoneNetworkMapper
    )
    private val peopleNetworkMapper: PeopleNetworkMapper = PeopleNetworkMapper(
        personDetailsNetworkMapper
    )

    // for Single Person call
    private val singleNumberNetworkMapper: SingleNumberNetworkMapper = SingleNumberNetworkMapper()
    private val emailNetworkMapper: SingleEmailNetworkMapper = SingleEmailNetworkMapper()
    private val singlePhoneNetworkMapper: SinglePhoneNetworkMapper = SinglePhoneNetworkMapper(singleNumberNetworkMapper)
    private val addressNetworkMapper: AddressNetworkMapper = AddressNetworkMapper()
    private val singlePersonDetailsNetworkMapper: SinglePersonDetailsNetworkMapper = SinglePersonDetailsNetworkMapper(
        nameNetworkMapper,
        addressNetworkMapper,
        singlePhoneNetworkMapper,
        emailNetworkMapper
    )
    private val personNetworkMapper: PersonNetworkMapper = PersonNetworkMapper(singlePersonDetailsNetworkMapper)

    override fun insert(person: Person) {
        queries.insertPerson(
            id = person.id,
            first_name = person.personDetails?.name?.first,
            last_name = person.personDetails?.name?.last,
            nick_name = person.personDetails?.name?.nick,
            maiden_name = person.personDetails?.name?.maiden,
            middle_name = person.personDetails?.name?.middle,
            profile_picture = person.personDetails?.profilePicture,
            archived = if(person.personDetails?.archived == 0) false else true,
            email = person.personDetails?.email
        )
    }

    override fun insert(people: List<Person>) {
        for (person in people){
            insert(person)
            insertPhone(person)
        }
    }

    override fun insertPhone(person: Person) {
        queries.insertPersonPhone(
            person_id = person.id,
            home = person.personDetails?.phone?.home,
            mobile = person.personDetails?.phone?.mobile,
            work = person.personDetails?.phone?.work
        )
    }

    override fun getAll(): List<Person> {
        val people = queries.getAllPeople().executeAsList()
        return peopleNetworkMapper.mapFromEntityList(people)
    }

    override fun get(personId: Int): SinglePerson? {
        return try {
            val person = queries.getPersonById(id = personId.toLong()).executeAsOne()
            return personNetworkMapper.mapFromEntity(person)
        }catch (e: NullPointerException){
            null
        }
    }


}