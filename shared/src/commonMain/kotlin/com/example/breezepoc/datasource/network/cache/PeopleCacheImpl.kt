package com.example.breezepoc.datasource.network.cache

import com.example.breezepoc.datasource.cache.PeopleDatabase
import com.example.breezepoc.datasource.cache.PeopleDbQueries
import com.example.breezepoc.domain.model.Person.SinglePerson

class PeopleCacheImpl(
    val peopleDatabase: PeopleDatabase
): PeopleCache {

    // located in shared build/generated/sqldelight folder
    // you can also see the functions in the PeopleDb.sq
    // sqldelight generates the classes from that file
    private var queries: PeopleDbQueries = peopleDatabase.peopleDbQueries

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

    override fun getAll(): List<SinglePerson> {
        return queries.getAllPeople().executeAsList()
    }

    override fun get(personId: Int): SinglePerson? {
        return try {
            queries
                .getPersonById(id = personId)
                .executeAsOne()
        }catch (e: NullPointerException){
            null
        }
    }


}