package Creational

/**
 * This pattern is mostly used when you have few classes implementing the same
 * interface.
 */
fun main(){

    /**
     * Beauty of this pattern is that your abstracted about the underlying
     * implementation.
     */

    //in case of no internet
    val personFromNetwork = PersonFactory.getPersonObject(PersonFactory.Companion.dataType.FROM_DB)

    //in case of network
    val personFromDb = PersonFactory.getPersonObject(PersonFactory.Companion.dataType.FROM_NETWORK)

    println("${personFromNetwork.getPersonalData()}")
    println("${personFromDb.getPersonalData()}")
}



class PersonFactory{
    companion object {
        enum class dataType{FROM_DB,FROM_NETWORK}
        fun getPersonObject(dataType: dataType):PersonData{
            return when(dataType){
                Companion.dataType.FROM_DB->{
                    dbData()
                }
                Companion.dataType.FROM_NETWORK->{
                    networkData()
                }
            }
        }
    }
}






interface PersonData{
    fun getPersonalData():Person
}

/**
 * This data could be coming from an persistance storage.
 */
class dbData:PersonData{

    private val person:Person

    constructor(){
        person = Person.PersonBuilder("karthik Network",28.0f)
                .setDob("12-04-1970")
                .setLastName("Rk")
                .setHomeAddress("#1234 2nd stage 2nd A main")
                .build()
    }


    override fun getPersonalData(): Person {
        return person
    }

}

/**
 * This could be coming over an async network call.
 */
class networkData:PersonData{

    private val person:Person

    constructor(){
        person = Person.PersonBuilder("karthik DB",28.0f)
                .setDob("12-04-1970")
                .setLastName("Rk")
                .setHomeAddress("#1234 2nd stage 2nd A main")
                .build()
    }

    override fun getPersonalData(): Person {
        return person
    }

}



