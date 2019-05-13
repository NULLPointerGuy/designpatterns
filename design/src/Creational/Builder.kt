package Creational


/**
 * Main Reasons/pros of using builder pattern
 * 1.Telescoping of constructors(doubt of which constructor to use).
 * only few params are req for creation others are optional.
 * 2.Encapsulate the actual object creation.
 * 3.Force immutability on the created object.
 *
 */
fun main(args:Array<String>){
    val person = Person.PersonBuilder("karthik",28.0f)
            .setDob("12-04-1970")
            .setLastName("Rk")
            .setHomeAddress("#1234 2nd stage 2nd A main")
            .build()

    println("Person value is:$person")
}


class Person{

    /**
     * Biggest con: repeat of fields.
     */
    private val name:String
    private val age:Float
    private val lastName:String
    private val address: String
    private val dob: String

    private constructor(name:String,age:Float,lastname:String,address: String,
                        dob:String){
        this.name = name
        this.age = age
        this.lastName = lastname
        this.address = address
        this.dob = dob
    }


    fun getName() = name

    data class PersonBuilder(private val name:String,private val age:Float){

        private lateinit var lastName:String
        private lateinit var address: String
        private lateinit var dob: String


        fun setLastName(last:String):PersonBuilder{
            this.lastName = last
            return this
        }

        fun setHomeAddress(address:String):PersonBuilder{
            this.address = address
            return this
        }

        fun setDob(dob:String): PersonBuilder {
            this.dob = dob
            return this
        }

        fun build():Person{

            /**
             * hiding the implementation of replacing uninitialized fields
             * with empty.
             */
            val lastname = if(::lastName.isInitialized) lastName else ""
            val address = if(::address.isInitialized) address else ""
            val dob = if(::dob.isInitialized) dob else ""

            return Person(name,age,lastname,address,dob)
        }
    }

    override fun toString(): String {
        return "Value of person:${this.name},${this.age},${this.dob},${this.address},${this.lastName}"
    }
}


