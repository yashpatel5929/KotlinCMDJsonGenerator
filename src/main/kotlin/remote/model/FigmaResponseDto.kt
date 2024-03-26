package remote.model

import com.google.gson.annotations.SerializedName

data class FigmaResponseDto(
    @SerializedName("status")
    val status: Int? = null,
    @SerializedName("err")
    val err: String? = null,
    @SerializedName("document")
    var document: Document? = Document()
)

data class Document(

    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("scrollBehavior") var scrollBehavior: String? = null,
    @SerializedName("children") var children: ArrayList<Children> = arrayListOf()

)


data class Children(
    val id: String? = null,
    val name: String? = null,
    val type: String? = null,
    val scrollBehavior: String? = null,
    val blendMode: String? = null,
    val absoluteBoundingBox: AbsoluteBoundingBox? = null,
    val absoluteRenderBounds: AbsoluteRenderBounds?,
    val constraints: Constraints? = null,
    val fills: List<Fill>? = listOf(),
    val strokeWeight: Double? = null,
    val strokeAlign: String? = null,
    val effects: List<Any?>? = null,
    val characters: String? = null,
    val style: Style? = null,
    val children: List<Children>? = null,
    val characterStyleOverrides: List<Long>? = null,
    val lineTypes: List<String>? = null,
    val lineIndentations: List<Long>? = null,
    val opacity: Double? = null,
    val cornerRadius: Double? = null,
    val cornerSmoothing: Long? = null,
    val clipsContent: Boolean? = null,
    val background: List<Background>? = null,
    val backgroundColor: Color? = null,
    val rectangleCornerRadii: List<Long>? = null,
    val booleanOperation: String? = null,
    val preserveRatio: Boolean? = null,
    val visible: Boolean? = null,
    val isMask: Boolean? = null,
    val maskType: String? = null,
    val layoutAlign: String? = null,
    val layoutGrow: Long? = null,
    val layoutSizingHorizontal: String? = null,
    val layoutSizingVertical: String? = null,
    val layoutVersion: Long? = null,
    val strokeJoin: String? = null,
    val strokeCap: String? = null,
    val layoutMode: String? = null,
    val layoutWrap: String? = null,
    val itemSpacing: Long? = null,
    val isMaskOutline: Boolean? = null,
    val componentId: String? = null,
)

data class AbsoluteBoundingBox(
    val x: Double,
    val y: Double,
    val width: Double,
    val height: Double,
)

data class AbsoluteRenderBounds(
    val x: Double,
    val y: Double,
    val width: Double,
    val height: Double,
)

data class Constraints(
    val vertical: String,
    val horizontal: String,
)

data class Fill(
    val blendMode: String,
    val type: String,
    val color: Color?,
    val visible: Boolean? = null,
    val scaleMode: String? = null,
    val imageRef: String? = null,
    val imageTransform: List<List<Double>>? = null,
)

data class Style(
    val fontFamily: String,
    val fontPostScriptName: String,
    val fontWeight: Long,
    val textAutoResize: String?,
    val fontSize: Double,
    val textAlignHorizontal: String,
    val textAlignVertical: String,
    val letterSpacing: Double,
    val lineHeightPx: Double,
    val lineHeightPercent: Double,
    val lineHeightUnit: String,
    val lineHeightPercentFontSize: Double?,
    val italic: Boolean?,
)

data class Color(
    val r: Double,
    val g: Double,
    val b: Double,
    val a: Double,
)

data class Background(
    val blendMode: String,
    val visible: Boolean,
    val type: String,
    val color: Color,
)