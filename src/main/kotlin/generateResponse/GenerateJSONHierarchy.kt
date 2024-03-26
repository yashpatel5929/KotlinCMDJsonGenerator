package generateResponse

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import locale.FileOperationsImpl
import remote.GetFigmaData
import remote.model.Children

class GenerateJSONHierarchy {

    private var imageUrlIdList: MutableList<String> = mutableListOf()
    suspend fun getJSONHierarchy(fileOperations: FileOperationsImpl, filePath: String?, authToken: String?): String {
        val rootJsonObject = JsonObject()
        //Taking file name as user input to read it and generate json hierarchy
        print("Enter file name you want to generate Json :- ")
        val fileName = readln()
        val element = fileOperations.readFromJsonFile<Children>(fileName)
        val jsonElement = element?.let { buildJsonHierarchy(it) }
        rootJsonObject.add(element?.id, jsonElement)
        //here I am making another api call to fetch all image/icon url used in given screen
        val result = GetFigmaData(filePath, authToken).getImageUrls(imageUrlIdList).await()
        rootJsonObject.add("imageUrls", result)
        return Gson().toJson(rootJsonObject)
    }


    private fun buildJsonHierarchy(element: Children): JsonObject {
        val jsonElement = JsonObject()
        val backgroundArray = JsonArray()
        val backgroundElement = JsonObject()


        jsonElement.addProperty("id", element.id)
        jsonElement.addProperty("idName", element.name)
        jsonElement.addProperty("type", element.type)
        jsonElement.addProperty("width", element.absoluteBoundingBox?.width)
        jsonElement.addProperty("height", element.absoluteBoundingBox?.height)
        if (element.type.equals("group", true)) {
            imageUrlIdList.add(element.id.toString())
            jsonElement.addProperty("imageUrlId", element.id)
            return jsonElement
        }
        if (element.type.equals("Text", true)) {
            jsonElement.addProperty("text", element.characters)
            element.style?.let {
                jsonElement.addProperty("fontFamily", it.fontFamily)
                jsonElement.addProperty("textSize", it.fontSize)
                jsonElement.addProperty("lineHeight", it.lineHeightPx)
                jsonElement.addProperty("weight", it.fontWeight)
                jsonElement.addProperty("align", it.textAlignHorizontal)
            }
        }
        if (!element.background.isNullOrEmpty()) {

            element.background.forEach {
                backgroundElement.addProperty("backgroundType", it.type)
                backgroundElement.addProperty("color", it.color.toString())
            }
            backgroundElement.addProperty("backgroundColor", element.backgroundColor.toString())
            backgroundArray.add(backgroundElement)
            jsonElement.add("Background", backgroundArray)
        }


        if (!element.fills.isNullOrEmpty()) {
            jsonElement.addProperty("fillsType", element.fills[0].type)
            jsonElement.addProperty("fillsColor", element.fills[0].color.toString())
        }


        element.children?.let { children ->
            val childrenJsonArray = JsonArray()
            children.forEach { child ->
                val childJson = buildJsonHierarchy(child)
                childrenJsonArray.add(childJson)
            }
            jsonElement.add("children", childrenJsonArray)
        }

        return jsonElement
    }


}