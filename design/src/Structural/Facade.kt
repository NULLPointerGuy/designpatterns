package Structural

/**
 * Similar to factory pattern itself only difference here is
 * in factory pattern we hide creation of the object,via interface.
 * where as here we hide implementation details after creation.
 * implementation is hidden behind `ShapeArea` interface.
 */
fun main(){
    CarFacade().startCar()
}


class CarFacade{
    private val engine:Engine
    private val battery:Battery
    private val carDashBoard:CarDashBoard

    constructor(){
        engine = Engine()
        battery = Battery()
        carDashBoard = CarDashBoard()
    }

    fun startCar(){
        engine.startEngine()
        battery.turnOnBattery()
        carDashBoard.turnOnDashBoard()
    }
}

class Engine(){
    fun startEngine(){
        println("Start Engine...")
    }
}

class Battery(){
    fun turnOnBattery(){
        println("Turn on battery...")
    }
}

class CarDashBoard(){
    fun turnOnDashBoard(){
        println("Turn on DashBoard")
    }
}

