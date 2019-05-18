package Creational


/**
 * This pattern is mainly required when you want a prototype object,
 * in which only basic initialization is done rest, object configuration is done
 * as and when the data comes.
 *
 * Adv:
 * It eliminates the (potentially expensive) overhead of initializing an object.
 */
fun main(){
    val protyper = PersonProtypeProvider()
    protyper.addPerson("Harry")
    protyper.addPerson("Dave")
    println(protyper.getProtoType("Harry").toString())
}


class PersonProtypeProvider{
    private var mapper = HashMap<String,PersonPrototyper>()

    fun addPerson(name:String) {
        when(name){
            "Harry"->{
                mapper.put("Harry",getHarryInstane())
            }
            "Dave"->{
                mapper.put("Harry",getDaveInstane())
            }
        }
    }

    fun getProtoType(name:String):Person?{
        if(mapper.containsKey(name)){
            return mapper[name]?.getPerson()
        }
        return null
    }

    /**
     * Imagine these two methods to be a heavy instance creation.
     */
    private fun getHarryInstane():PersonPrototyper{
        return Harry(Person.PersonBuilder("Harry",28.0f)
                .setDob("12-04-1970")
                .setLastName("Rk")
                .setHomeAddress("#1234 2nd stage 2nd A main")
                .build())
    }

    private fun getDaveInstane():PersonPrototyper{
        return Dave(Person.PersonBuilder("Harry",28.0f)
                .setDob("12-04-1970")
                .setLastName("Rk")
                .setHomeAddress("#1234 2nd stage 2nd A main")
                .build())
    }
}

interface PersonPrototyper{
    fun getPerson():Person
}

class Harry:PersonPrototyper,Cloneable{
    private val person:Person

    constructor(person: Person) {
        this.person = person
    }

    /**
     * Provide a very prototypical instance.
     * with min properties set
     */
    override fun getPerson(): Person {
        return Person.PersonBuilder("Harry",28.0f)
                .build()
    }
}

class Dave:PersonPrototyper,Cloneable{
    private val person:Person

    constructor(person: Person) {
        this.person = person
    }

    /**
     * Provide a very prototypical instance.
     * with min properties set
     */
    override fun getPerson(): Person {
        return Person.PersonBuilder("Dave",32.0f)
                .build()
    }
}


