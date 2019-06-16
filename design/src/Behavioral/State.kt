package Behavioral


/**
 * Pattern in which "Object's behaviour changes" when it's internal state changes.
 * based on "Finite state machine".
 */
fun main(){
    val hello = "This is simple string"
    val printer = Printer(State.LOWERCASE)
    printer.print(hello)

    printer.setState(State.UPPERCASE)
    printer.print(hello)

    printer.setState(State.CAMELCASE)
    printer.print(hello)
}


/**
 * Different states.
 */
enum class State {
   LOWERCASE,
   UPPERCASE,
   CAMELCASE,
}


interface StateAware{
    fun setState(state:State)
}

//state aware printer.
class Printer:StateAware{
    private var state:State
    constructor(intialState:State){
        state = intialState
    }

    override fun setState(state: State) {
        this.state = state
    }

    fun print(words:String){
        when(state){
            State.LOWERCASE->{
                println(words.toLowerCase())
            }
            State.UPPERCASE->{
                println(words.toUpperCase())
            }
            State.CAMELCASE->{
                val splitwords = words.split(" ")
                for(word in splitwords){
                    kotlin.io.print(word.replaceFirst(word[0],word[0].toUpperCase())+" ")
                }
                println()
            }
        }
    }
}







