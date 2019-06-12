package Behavioral


/**
 * Pattern in which "subject", maintains list of "observers" and notifies them
 * in case of any state changes.
 *
 * Ex: Messaging group where any message is added has to be sent to
 * users in the group.
 */
fun main(){

    val group1 = MessagingGroup("Group1")
    val sam = Sam()
    group1.addObservers(sam)
    val john = John()
    group1.addObservers(john)


    val group2 = MessagingGroup("Group2")
    val dave = Dave()
    group2.addObservers(dave)
    val mathew = Mathew()
    group2.addObservers(mathew)


    /**
     * Observers get message from the subject they are observing
     */
    group1.addMessage("hello")
    group2.addMessage("welcome")
}

interface Observer{
    fun notify(groupName:String,msg:String)
}

class Sam:Observer{
    override fun notify(groupName:String,msg: String) {
        println("Sam got msg: $msg")
    }
}

class John:Observer{
    override fun notify(groupName:String,msg: String) {
        println("John got msg: $msg")
    }
}

class Dave:Observer{
    override fun notify(groupName:String,msg: String) {
        println("Dave got msg: $msg")
    }

}

class Mathew:Observer {
    override fun notify(groupName:String,msg: String) {
        println("Mathew got msg: $msg")
    }
}

class MessagingGroup(private val name:String){
    private lateinit var observers:ArrayList<Observer>
    private lateinit var messages:ArrayList<String>

    fun addObservers(observer: Observer){
        if(!::observers.isInitialized){
            observers = ArrayList()
        }
        observers.add(observer)
    }

    fun addMessage(msg: String){
        if(!::messages.isInitialized){
            messages = ArrayList()
        }
        messages.add(msg)

        notifyObservers(msg)
    }

    private fun notifyObservers(msg: String) {
        if (::observers.isInitialized && observers.isNotEmpty()) {
            for (observer in observers)
                observer.notify(name,msg)
        }
    }
}
