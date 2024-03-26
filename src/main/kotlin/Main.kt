import remote.GetFigmaData
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    /*println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")*/

    //Taking file path and figma auth token from user as input
    print("Enter file path :- ")
    val filePath = readlnOrNull()
    print("enter Token :- ")
    val authToken = readlnOrNull()

    if(!filePath.isNullOrEmpty() && !authToken.isNullOrEmpty()) {
        GetFigmaData(filePath,authToken).apply {
            getFigmaDataResponse()
        }
    }else {
        println("Please Provide Required Data!")
        exitProcess(0)
    }
}