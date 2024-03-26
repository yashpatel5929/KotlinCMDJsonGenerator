package remote.network

import remote.model.FigmaResponseDto
import remote.model.ImageUrls
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("files/{filePath}")
    suspend fun getFigmaData(
        @Header("X-FIGMA-TOKEN") toke: String,
        @Path("filePath") filePath: String
    ): Response<FigmaResponseDto>


    @GET("images/{filePath}")
    suspend fun getImageUrls(
        @Header("X-FIGMA-TOKEN") token: String,
        @Path("filePath") filePath: String,
        @Query("ids") ids: String
    ): Response<ImageUrls>
}