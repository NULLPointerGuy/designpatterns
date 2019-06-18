package Behavioral

/**
 * Main agenda of this pattern is "new behavior into a separate class called visitor,
 * instead of trying to integrate it into existing classes".
 *
 * Ex: In this all the behavioural changes are put in separate class like EShoppingCart
 * for discounts etc.
 *
 * Con: As the new behavior increases visitor class grows
 */
fun main(){
    val book = Book(30.0f)
    val pen = Pen(15.0f)
    val shoppingCart = ShoppingCart()
    book.apply(shoppingCart)
    pen.apply(shoppingCart)
    println("The total price using shopping cart :${shoppingCart.getTotal()}")

    val eShoppingCart = EShoppingCart()
    book.apply(eShoppingCart)
    pen.apply(eShoppingCart)
    println("The total price using e-shopping cart :${eShoppingCart.getTotal()}")
}


interface VisitingElements{
    fun apply(visitor: Visitor)
    fun getElementPrice():Float
}

/**
 * All new behavior are put into this class.
 */
interface Visitor{
    fun apply(elements: VisitingElements)
    fun getTotal():Float
}


class Book:VisitingElements{
    private var price:Float
    constructor(price:Float){
       this.price = price
    }
    override fun getElementPrice(): Float = price
    override fun apply(visitor: Visitor) = visitor.apply(this)
}

class Pen:VisitingElements{
    private var price:Float
    constructor(price:Float){
        this.price = price
    }
    override fun apply(visitor: Visitor) = visitor.apply(this)
    override fun getElementPrice(): Float = price
}


class Computer:VisitingElements{
    private var price:Float
    constructor(price:Float){
        this.price = price
    }
    override fun apply(visitor: Visitor) = visitor.apply(this)
    override fun getElementPrice(): Float = price
}


class ShoppingCart:Visitor{
    private var totalPrice:Float=0.0f
    override fun apply(elements: VisitingElements) {
        totalPrice+=elements.getElementPrice()
    }
    override fun getTotal(): Float = totalPrice
}

/**
 * 15% discount on everything bought online.
 */
class EShoppingCart:Visitor{
    private var totalPrice:Float=0.0f
    override fun apply(elements: VisitingElements) {
       totalPrice+=(elements.getElementPrice()-elements.getElementPrice()*0.15).toFloat()
    }
    override fun getTotal(): Float = totalPrice
}


