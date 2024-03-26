package locale

import com.google.gson.Gson
import java.io.File

class FileOperationsImpl : FileOperations {
    override fun writeToJsonFile(data: Any, fileName: String) {
        val jsonString = Gson().toJson(data)
        try {
            val file = File(fileName)
            file.writeText(jsonString)
            println("Data written to file: $fileName")
        } catch (e: Exception) {
            println("Error writing to file: ${e.message}")
        }
    }

    inline fun <reified T> readFromJsonFile(fileName: String): T? {
        val file = File(fileName)

        return if (file.exists()) {
            try {
                val jsonString = file.readText()
                Gson().fromJson(jsonString, T::class.java)
            } catch (e: Exception) {
                println("Error reading from file: ${e.message}")
                null
            }
        } else {
            println("File not found: $fileName")
            null
        }
    }
}