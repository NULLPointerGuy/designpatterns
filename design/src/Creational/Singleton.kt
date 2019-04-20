package Creational
fun main(args:Array<String>){
    Singleton.helloword()
}

/**
 * Typically used if you want to maintain single instance across the application.
 * reasons could be creation of resources everytime would be heavy and time taking.
 * Singleton in kotlin is achieved using object keyword.
 * Ex: NetworkProvider.
 */
object Singleton {
    val immutablevalue = 5
    fun helloword() = print("Hello World, this is singleton $immutablevalue")
}