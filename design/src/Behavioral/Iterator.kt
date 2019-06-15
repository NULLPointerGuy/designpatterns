package Behavioral


/**
 * Iterator pattern allows sequential traversal of any complex DS with exposing
 * underlying implementation.
 *
 * In this example we are hiding the last element in Stack without consumer
 * knowing about it.
 */
fun main(){
    val array = ArList<String>()
    array.add("hello")
    array.add("hi")
    array.add("tmp")
    iteratAndPrint(array)

    val stack = Stack<String>()
    stack.pushData("hello")
    stack.pushData("hi")
    stack.pushData("tmp")
    iteratAndPrint(stack)


    val queue  = Queue<String>()
    queue.addData("hello")
    queue.addData("hi")
    queue.addData("tmp")
    iteratAndPrint(queue)

}



private fun iteratAndPrint(iterator:Iterator<String>){
    while(iterator.hasNext()){
        println(iterator.next())
    }
}


interface Iterator<T>{
    fun hasNext():Boolean
    fun next():T
}

class ArList<T>:Iterator<T>{
    private lateinit var array:MutableList<T>
    private var index=-1

    override fun hasNext(): Boolean  = index+1<array.size

    override fun next(): T {
        index++
        return array[index]
    }

    fun add(data:T){
         if(!::array.isInitialized){
             array = mutableListOf()
         }
        array.add(data)
    }
    fun remove(data: T){
        array.remove(data)
    }
}


class Stack<T>:Iterator<T>{
    private lateinit var array:MutableList<T>
    private var index=-1

    override fun hasNext(): Boolean = index+1<array.size-1

    override fun next(): T {
        index++
        return array[index]
    }

    fun pushData(data:T){
        if(!::array.isInitialized){
            array = mutableListOf()
        }
        array.add(data)
    }

    fun popData():T = array.removeAt(array.lastIndex)
}

class Queue<T>:Iterator<T>{
    private lateinit var array:MutableList<T>
    private var index=-1

    override fun hasNext(): Boolean = index+1<array.size

    override fun next(): T {
        index++
        return array[index]
    }

    fun addData(data:T){
        if(!::array.isInitialized){
            array = mutableListOf()
        }
        array.add(data)
    }

    fun removeData():T = array.removeAt(0)
}