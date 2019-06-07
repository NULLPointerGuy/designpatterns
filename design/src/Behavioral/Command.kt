package Behavioral


/**
 * Main purpose of this pattern is to encapsulate all the information
 * required to perform an action / trigger an event.
 *
 * It is used when you want to QUEUE the req or executes of sequence of operations
 * before executing the request.
 *
 * Eg: VehicleCommander is the class responsible for starting, stopping
 * and refilling the fuel.
 *
 * Separation of concern, is the main address here,
 *  So that the business or the GUI need not worry about it.
 */
fun main(){
    val vehicleCommander = VehicleCarCommand(Vehicle(true,24))

    if(!vehicleCommander.startCar()){
        vehicleCommander.refillFuel()
        vehicleCommander.startCar()
    }

    vehicleCommander.stopCar()
}

class Vehicle(private val isDark:Boolean,private var fuel:Int){
    fun getFuelCapacity() = 100
    fun isLowOnFuel() = fuel<25
    fun startCar(){ println("Starting the car") }
    fun stopCar(){ println("Stopping the car") }
    fun turnOnLights() = println("Turning on lights")
    fun turnOffLights() = println("Turning off lights")
    fun isDark() = isDark
    fun addFuel(fuel: Int){
        this.fuel+=fuel
    }
    fun getFuel() = fuel
}

interface VehicleCommander{
    fun startCar():Boolean
    fun stopCar():Boolean
    fun refillFuel()
}


/**
 * Abstracts the start command by checking fuel, should turn on lights.
 * Also refill fuel logic is hidden / separated.
 */
class VehicleCarCommand(private val vehicle: Vehicle):VehicleCommander{
    override fun startCar():Boolean {
        if(vehicle.isLowOnFuel()){
            println("Please fill the fuel and start car")
            return false
        }
        if(vehicle.isDark()){
            vehicle.turnOnLights()
        }else{
            vehicle.turnOffLights()
        }
        vehicle.startCar()
        return true
    }

    override fun stopCar():Boolean {
        vehicle.stopCar()
        return true
    }

    /**
     * refill 75% of the fuel
     */
    override fun refillFuel(){
        val maxfuel = 0.75*vehicle.getFuelCapacity()
        val fuelRequired = maxfuel-vehicle.getFuel()
        vehicle.addFuel(fuelRequired.toInt())
    }
}