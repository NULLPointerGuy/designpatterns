package Structural

import Creational.Person

/**
 * This pattern is mainly used as a wrapper / abstracting the underlying classes.
 */
fun main(){
    val proxybuilder = ProxyPersonBuilder()
    proxybuilder.intialize("Harish",12f,"12-12-12",
            "#1234 xzy 2nd A abc")
    println(proxybuilder.getPerson().toString())
}

/**
 * Main adv of proxy class is that it hides complexity of the class
 * for which it is a proxy.
 *
 * Eg: SVGImageLoader to load svg images using svg library and rx java.
 *
 * In the below example wrapper for person class.
 */
class ProxyPersonBuilder{
    private lateinit var person: Person

    fun intialize(name:String,age:Float,dob:String="",homeAddress:String=""){
        person = Person.PersonBuilder(name,age)
                .setDob(dob)
                .setHomeAddress(homeAddress)
                .build()
    }

    fun getPerson():Person?{
        if(::person.isInitialized){
            return person
        }
        return null
    }
}