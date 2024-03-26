package locale

interface FileOperations {
    fun writeToJsonFile(data : Any , fileName: String)
}