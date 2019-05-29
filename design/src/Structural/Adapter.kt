package Structural

import Creational.Person

/**
 * An Wrapper class that converts that connects in-compatible types.
 * Real world Eg: Ethernet Cable<-----Adapter B/W----->USB-C
 * Eg:Adapter in android.
 *
 *
 */
fun main(){

    val personsDirectory = Directory(getPersonsList())
    val personsAdapter = PersonsAdapter()


    val personsLastNameDirectory = personsAdapter.getPersonsLastNameDirectory(personsDirectory)
    val map = personsLastNameDirectory.getPersonsLastNamesDirectory()
    for(key in map.keys){
        println("$key last name:${map[key]}")
    }

    val personsNewDirectory = personsAdapter.getPersonsDirectory(personsLastNameDirectory)
    for(person in personsNewDirectory.getPersonsList()){
        println("Persons:${person.getName()}")
    }
}

/**
 * PersonDirectory-------->|Adapter|-------->PersonsLastNameDirectory
 */
class PersonsAdapter{
    fun getPersonsLastNameDirectory(directory: PersonDirectory)
            :PersonsLastNameDirectory{
        val personsLastNameDirectory = HashMap<Person,String>()

        for(person in directory.getPersonsList())
            personsLastNameDirectory[person] = person.getLastName()

        return LastNameDirectory(personsLastNameDirectory)
    }

    fun getPersonsDirectory(lastNameDirectory: PersonsLastNameDirectory)
            :PersonDirectory{
        return Directory(lastNameDirectory
                .getPersonsLastNamesDirectory().keys.toList())
    }
}


interface PersonDirectory{
    fun getPersonsList():List<Person>
}

interface PersonsLastNameDirectory{
    fun getPersonsLastNamesDirectory():HashMap<Person,String>
}

class Directory:PersonDirectory{
    private val persons:List<Person>
    constructor(persons:List<Person>){
        this.persons = persons
    }

    override fun getPersonsList():List<Person> = persons
}

class LastNameDirectory:PersonsLastNameDirectory{
    private val personsLastnames:HashMap<Person,String>

    constructor(persons:HashMap<Person,String>){
        this.personsLastnames = persons
    }

   override fun getPersonsLastNamesDirectory():HashMap<Person,String>
           = personsLastnames
}



private fun getPersonsList(): MutableList<Person> {
    val personsList = mutableListOf<Person>()
    for (i in 1..10) {
        personsList.add(Person.PersonBuilder(i.toString(), i + 10.0f)
                .setDob("$i:$i:$i")
                .setLastName("i:$i")
                .setHomeAddress("#1234 qwkljalc lkdnlaksd andlskd")
                .build())
    }
    return personsList
}