package Creational

import java.util.*

/**
 * This pattern is mainly used when creation of objects are expensive.
 * We create pool which maintains objects for a certain period of time.
 * one thing to note here is pool is responsible for object creation and releasing it.
 */
fun main(){
    val pool = PersonPool()
    pool.putobject(Person.PersonBuilder("karthik DB",28.0f)
            .setDob("12-04-1970")
            .setLastName("Rk")
            .setHomeAddress("#1234 2nd stage 2nd A main")
            .build())

    println(pool.getobject("karthik DB"))
}



/**
 * Simple implementation of person pool.
 */
class PersonPool:Pool<Person>(){
    override fun putobject(t: Person) {
        map[t] = Calendar.getInstance().timeInMillis
    }

    fun getobject(name:String):Person? {
        val time= Calendar.getInstance().timeInMillis
        for(keys in map.keys){
            if(keys.getName()==name && time-map[keys]!!<expiryTime){
               return keys
            }
            //time expired clear the object.
            else if(map.containsKey(keys) && time-map[keys]!!>expiryTime){
                map.remove(keys)
            }
        }
        return null
    }
}



/**
 * Basic skeleton of pool.
 */
abstract class Pool<T>{
    protected var expiryTime = 6000
    protected val map:HashMap<T,Long> = HashMap()
    protected var poolSize:Int=3


    constructor(poolSize:Int=3,expiryTime:Int = 6000){
        this.poolSize = poolSize
        this.expiryTime = expiryTime
    }
    abstract fun putobject(t:T)
}