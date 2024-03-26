package remote

import com.google.gson.JsonObject
import generateResponse.GenerateJSONHierarchy
import kotlinx.coroutines.*
import locale.FileOperationsImpl
import remote.network.ApiClient
import kotlin.system.exitProcess

class GetFigmaData(val filePath: String?=null, val authToken: String ?=null) {

    val job = CoroutineScope(Dispatchers.Default + Job())
    val fileOperationsImpl by lazy {
        FileOperationsImpl()
    }

    fun getFigmaDataResponse() {
        print("Loading.....")
        val result = job.launch {
            try {
                val response = ApiClient.apiService.getFigmaData(authToken?.trim() ?: "", filePath?.trim() ?: "")
                if (response.isSuccessful) {
                    val responseBody = response.body()!!
                    if (responseBody.status == 403 || responseBody.status == 404 || responseBody.status == 402) {
                        print("Status Code :- ${responseBody.status} error :- ${responseBody.err}")
                    } else {
                        //As per requirement we need to stored SignUp and Login screen data in separate file. i am taking node is as an input
                        //for sign up id :- 1409:1435 and for login :-1409:1516
                        print("Please Provide file Id :- ")
                        val fileIds = readln()
                        responseBody.document?.children?.get(0)?.children?.firstOrNull() {
                            it.id == fileIds
                        }?.also { element ->
                            //Storing Data in file
                            fileOperationsImpl.writeToJsonFile(
                                element,
                                element.name?.trim()?.replace(" ", "_").toString()
                            )
                        }
                        val result = GenerateJSONHierarchy().getJSONHierarchy(fileOperationsImpl,filePath,authToken)
                        print("$result")
                        exitProcess(0)
                    }
                }
            } catch (e: Exception) {
                print("${e.message}")
            }
        }

        runBlocking {
            result.join()
            if (!job.isActive) {
                job.cancel()
                println("Job cancelled")
            }
        }
    }


     fun getImageUrls(imageUrl : List<String>) = CoroutineScope(Dispatchers.IO).async {
            var imageJsonObject  : JsonObject = JsonObject()
            try {
                val response =  ApiClient.apiService.getImageUrls(authToken!! ,filePath!!,imageUrl.joinToString(","))
                if(response.isSuccessful) {
                    val responseBody = response.body()!!
                    if (responseBody.status == 403 || responseBody.status == 404 || responseBody.status == 402) {
                        print("${responseBody.status}")
                    } else {
                        imageJsonObject = responseBody.images!!
                    }
                }
            }catch (e:Exception) {
                print("${e.message}")
            }
            imageJsonObject
    }

}