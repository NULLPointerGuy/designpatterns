package Structural

/**
 *
 * Use this pattern whenever there is two orthogonal dimensions, as shown below.
 *
 *         A
        /     \
      Aa      Ab
     / \     /  \
    Aa1 Aa2  Ab1 Ab2

        to

        A                N
      /  \              / \
     Aa(N)Ab(N)        1   2
 *
 *
 *
 *
 * Pattern that divides single monolithic class into several class
 * with it's own hierarchy.
 *
 * Eg:Device has a Remote relationship, where remote has its own hierarchy
 * and device has its own.
 */
fun main(){
    val tv = Tv(UniversalRemote())
    println("${tv.getName()}:  ${tv.turnOn()}:  ${tv.turnOff()}")

    val musicSystem = MusicSystem(AdvancedRemote())
    println("${musicSystem.getName()}:  ${musicSystem.turnOn()}:  ${musicSystem.turnOff()}")

    val thermostat = Thermostat(SimpleRemote())
    println("${thermostat.getName()}:  ${thermostat.turnOn()}:  ${thermostat.turnOff()}")
}


interface Device{
    fun getName():String
    fun turnOn():String
    fun turnOff():String
}


interface Remote{
    fun turnOff():String
    fun turnOn():String
}


/**
 * Device hierarchy
 */
class Tv:Device{
    private val remote:Remote

    constructor(remote:Remote){
        this.remote = remote
    }

    override fun getName()= "Tv"

    override fun turnOn()  = remote.turnOn()

    override fun turnOff() = remote.turnOff()
}

class MusicSystem:Device{
    private val remote:Remote

    constructor(remote:Remote){
        this.remote = remote
    }

    override fun getName(): String = "Music system"

    override fun turnOn() = remote.turnOn()

    override fun turnOff()= remote.turnOff()
}


class Thermostat:Device{
    private val remote:Remote

    constructor(remote:Remote){
        this.remote = remote
    }


    override fun getName(): String = "Thermostat"

    override fun turnOn() = remote.turnOn()

    override fun turnOff() = remote.turnOff()
}




/***
 * Remote hierarchy.
 */
class SimpleRemote:Remote{
    override fun turnOff() = "turning off"

    override fun turnOn()  = "turning on"
}

class AdvancedRemote:Remote{
    override fun turnOff() = "turning off advanced remote"

    override fun turnOn() = "turning on advanced remote"
}

class UniversalRemote:Remote{
    override fun turnOff() = "turning off universal remote"

    override fun turnOn() = "turning on universal remote"
}