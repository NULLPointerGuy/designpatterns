package Structural


/**
 * "Smart Proxy pattern" in which instead of extending functionality statically
 *  via inheritance, you opt for has a relationship.
 *
 *  for eg: in below IceCreamCake is made using black current, you can simply change
 *  the functionality by passing chocolate tomorrow, as getDescritption() changes
 *  for each type of flavours.
 */
fun main(){
    val iceCream = vaniallaIceCream()
    val blackcurrent = BlackCurrent(iceCream)
    val chocolate= chocolate(iceCream)
    val IceCreamCake= IceCreamCake(blackcurrent)

    blackcurrent.addFlavour("chocko chips")


    val arrauy = ArrayList<String>()
    arrauy.add("choocko chips")
    arrauy.add("black cherrys")
    chocolate.addFlavour(arrauy.toTypedArray())

    IceCreamCake.addFlavour("chocko chips",10)

    println(blackcurrent.getDescritption())
    println(chocolate.getDescritption())
    println(IceCreamCake.getDescritption())
}

interface Icecream{
 fun stirSugarCream()
 fun pourVanilaExtractAndChillMix()
 fun pourMixToIceCreamMakerAndWait()
 fun getIceCreamDescription():String
}


class vaniallaIceCream:Icecream{
    override fun stirSugarCream() {
        println("Stir sugar, cream, and milk into a saucepan over low heat until sugar has dissolved. " +
                "Heat just until mix is hot and a small ring of foam appears around the edge")
    }
    override fun pourVanilaExtractAndChillMix() {
        println("Transfer cream mixture to a pourable container such as a large measuring cup. Stir in vanilla extract " +
                "and chill mix thoroughly, at least 2 hours. (Overnight is best.)")
    }
    override fun pourMixToIceCreamMakerAndWait() {
        println("Pour cold ice cream mix into an ice cream maker, turn on the machine, and churn according " +
                "to manufacturer's directions, 20 to 25 minutes.")
    }
    override fun getIceCreamDescription(): String="Simple vanilla ice cream"
}

/**
 * Ice-cream decorator
 */
abstract class IceCreamFlavours:Icecream{
    protected val icecream:Icecream
    constructor(icecream: Icecream){
        this.icecream = icecream
        makeIceCream()
    }
    override fun stirSugarCream() {
        println("Stir sugar, cream, and milk into a saucepan over low heat until sugar has dissolved. " +
                "Heat just until mix is hot and a small ring of foam appears around the edge")
    }
    override fun pourVanilaExtractAndChillMix() {
        println("Transfer cream mixture to a pourable container such as a large measuring cup. Stir in vanilla extract " +
                "and chill mix thoroughly, at least 2 hours. (Overnight is best.)")
    }
    override fun pourMixToIceCreamMakerAndWait() {
        println("Pour cold ice cream mix into an ice cream maker, turn on the machine, and churn according " +
                "to manufacturer's directions, 20 to 25 minutes.")
    }
    fun makeIceCream(){
        stirSugarCream()
        pourVanilaExtractAndChillMix()
        pourMixToIceCreamMakerAndWait()
    }
    override fun getIceCreamDescription(): String = icecream.getIceCreamDescription()
    abstract fun getDescritption():String
}

class BlackCurrent:IceCreamFlavours{
    private lateinit var flavour:String
    constructor(icecream: Icecream):super(icecream)
    fun addFlavour(flavour: String) {
        this.flavour = flavour
    }
    override fun getDescritption(): String = getIceCreamDescription()+"with $flavour"
}

class chocolate:IceCreamFlavours{
    private lateinit var flavour:Array<String>
    private val cherry = 4
    constructor(icecream: Icecream):super(icecream)
    fun addFlavour(flavour: Array<String>) {
        this.flavour = flavour
    }
    override fun getDescritption(): String{
        var desc = icecream.getIceCreamDescription()+" with  "
        for(i in flavour){
            desc+= i
        }
        return desc
    }
}


class IceCreamCake:IceCreamFlavours{
    private lateinit var flavour:String
    private  var cherry:Int = 4
    constructor(icecream: Icecream):super(icecream)
    fun addFlavour(flavour: String,cherry:Int) {
        this.flavour = flavour
        this.cherry = cherry
    }
    override fun getDescritption(): String = icecream.getIceCreamDescription()+" $flavour with $cherry cherrys"
}
