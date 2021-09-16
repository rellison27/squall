package com.example.breezepoc.datasource.network.cache

import com.example.breezepoc.datasource.cache.PeopleDatabase
import com.example.breezepoc.datasource.cache.PeopleDbQueries
import com.example.breezepoc.datasource.network.mappers.people.*
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

    private val numberNetworkMapper: NumberNetworkMapper = NumberNetworkMapper()
    private val phoneNetworkMapper: PhoneNetworkMapper = PhoneNetworkMapper(numberNetworkMapper)
    private val addressNetworkMapper: AddressNetworkMapper = AddressNetworkMapper()
    // for People
    private val nameNetworkMapper: NameNetworkMapper = NameNetworkMapper()
    private val emailNetworkMapper: EmailNetworkMapper = EmailNetworkMapper()
    private val peopleNetworkMapper: PeopleNetworkMapper = PeopleNetworkMapper(
        nameNetworkMapper,
        phoneNetworkMapper,
        addressNetworkMapper,
        emailNetworkMapper
    )

    // for Single Person call
    private val singleEmailNetworkMapper: SingleEmailNetworkMapper = SingleEmailNetworkMapper()

    private val singlePersonDetailsNetworkMapper: SinglePersonDetailsNetworkMapper = SinglePersonDetailsNetworkMapper(
        nameNetworkMapper,
        addressNetworkMapper,
        phoneNetworkMapper,
        singleEmailNetworkMapper
    )
    private val personNetworkMapper: PersonNetworkMapper = PersonNetworkMapper(singlePersonDetailsNetworkMapper)

    override fun insert(person: Person) {
        queries.insertPerson(
            id = person.id,
            first_name = person.name?.first,
            last_name = person.name?.last,
            nick_name = person.name?.nick,
            maiden_name = person.name?.maiden,
            middle_name = person.name?.middle,
            profile_picture = person.profilePicture,
            archived = person.archived,
            birthdate = person.birthdate,
            email = person.email?.address
        )
    }

    override fun insert(people: List<Person>) {
        for (person in people){
            insert(person)
            insertPhone(person)
            insertAddress(person)
        }
    }

    // TODO(Add home/mobile/work_is_private columns to db table)
    override fun insertPhone(person: Person) {
        queries.insertPersonPhone(
            person_id = person.id,
            home = person.phone?.home?.number,
            mobile = person.phone?.mobile?.number,
            work = person.phone?.work?.number,
            home_is_private = person.phone?.home?.private,
            mobile_is_private = person.phone?.mobile?.private,
            work_is_private = person.phone?.work?.private
        )
    }

    override fun insertAddress(person: Person) {
        queries.insertPersonAddress(
            person_id = person.id,
            street = person.address?.street,
            city = person.address?.city,
            state = person.address?.state,
            zip = person.address?.zip,
            longitude = person.address?.longitude,
            latitude = person.address?.latitude,
            private_ = person.address?.private
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