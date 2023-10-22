package android.com.nasaapp.network

import android.com.nasaapp.model.NasaInfo
import retrofit2.http.GET

interface NasaApiService {
    @GET("amphibians")
    suspend fun getInfo(): List<NasaInfo>
}