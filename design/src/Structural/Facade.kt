package Structural

/**
 * Similar to factory pattern itself only difference here is
 * in factory pattern we hide creation of the object,via interface.
 * where as here we hide implementation details after creation.
 * implementation is hidden behind `ShapeArea` interface.
 */
fun main(){
    val facade = FacadeNavigator(circle(11f), square(12f),rectangle(13f,14f))

    /**
     * facade Navigator decides where to redirect the getShapeArea() method call
     */
    println("${facade.getShapeArea("Circle")}")
    println("${facade.getShapeArea("Square")}")
    println("${facade.getShapeArea("Rectangle")}")
}


interface ShapeArea{
    fun getShapeArea():Float
}


class FacadeNavigator {

    private val circle: circle
    private val square: square
    private val rectangle: rectangle

    /**
     * Usually these instance are created using factory pattern, but for the
     * example purpose we will just inject in constructor.
     */
    constructor(circle: circle,square: square,rectangle: rectangle){
        this.circle = circle
        this.rectangle = rectangle
        this.square = square
    }

    fun getShapeArea(shape:String):Float{
        return when(shape){
            "Circle"->{
                this.circle.getShapeArea()
            }
            "Square"->{
                this.square.getShapeArea()
            }
            "Rectangle"->{
                this.rectangle.getShapeArea()
            }
            else->{
                println("Shape not recognized")
                0f
            }
        }
    }
}

class circle:ShapeArea{

    private val radius:Float

    constructor(radius:Float){
        this.radius = radius
    }


    override fun getShapeArea(): Float {
        return (22/7)*radius*radius
    }
}

class rectangle:ShapeArea{

    private val length:Float
    private val width:Float

    constructor(length:Float,width:Float){
        this.length = length
        this.width = width
    }

    override fun getShapeArea(): Float {
        return length*width
    }

}

class square:ShapeArea{

    private val length:Float

    constructor(length: Float){
        this.length = length
    }

    override fun getShapeArea(): Float {
        return length*length
    }

}