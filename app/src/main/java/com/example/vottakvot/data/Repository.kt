package com.example.vottakvot.data

import android.os.Build
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import com.example.vottakvot.database.BodyType
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.lang.reflect.Type


// https://www.youtube.com/watch?v=8zPkbV4INGA
// Gson Android Kotlin Tutorial - Parse Generic Lists from JSON & Introduction


class Repository {
    val BASE_URL = "https://exercisedb.p.rapidapi.com/exercises"
    val X_RapidAPI_Key = "89869e62a7msh8aae2e413f008cap11c8c6jsn17ad74ef16d0"
        //"1e2a994b1amshca6105b44e649b1p1193cbjsn68a76ceb1446"
        //"89869e62a7msh8aae2e413f008cap11c8c6jsn17ad74ef16d0"
    val X_RapidAPI_Host = "exercisedb.p.rapidapi.com"
    //bodyPart/back?limit=10"

    lateinit var worcoutListEntity: List<WorcoutListEntity.ExerciseEntity>

    public fun makeBodyTypeRequest(bodyPart: BodyType, limit: Int): Boolean {
        var url = BASE_URL + "/bodyPart/"
        when (bodyPart) {
            BodyType.UPPER_BODY -> url += "upper%20arms"
            BodyType.BOTTOM_BODY -> url += "upper%20legs"
            BodyType.ABD -> url += "waist"
            BodyType.FULL_BODY -> url += "cardio"

            else -> url += "cardio"
        }
        url += "?limit=" + limit

        return makeRequest(url)
    }

    public fun makeAllWorkoutsRequest(limit: Int): Boolean {
        var url = BASE_URL + "?limit=" + limit
        return makeRequest(url)
    }




    public fun makeRequest(url: String): Boolean {
        val SDK_INT = Build.VERSION.SDK_INT
        if (SDK_INT > 8) {
            val policy = ThreadPolicy.Builder()
                .permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        try {
            val parsedResponse = parseResponse(
                okHttpClient.newCall(
                    createRequest(url)
                )
                    .execute()
            )
            println(parsedResponse)
            return true
        } catch (e: Exception) {
            val a = e.message
            println(e.message)
            return false
        }

    }


    fun createRequest(url: String):Request {
        return Request.Builder()
            .url(url)
            .get()
            .addHeader("X-RapidAPI-Key", X_RapidAPI_Key)
                // "568e635a7fmsh455f805266f27cdp16734fjsn3917f09b41c7"
                //"6929bc99b9mshdb1a50796fb5502p111639jsn62b0b1df3969")
            .addHeader("X-RapidAPI-Host", X_RapidAPI_Host)
            .build()
    }

    inline fun <reified T> parseArray(json: String, typeToken: Type): T {
        val gson = GsonBuilder().create()
        return gson.fromJson<T>(json, typeToken)
    }

    // com.google.gson.internal.LinkedTreeMap cannot be cast to my class
    // из-за стирания типа парсер не может получить реальный тип T во время выполнения.
    // Одним из обходных путей было бы предоставить тип класса в качестве параметра метода.
    // https://stackoverflow.com/questions/27253555/com-google-gson-internal-linkedtreemap-cannot-be-cast-to-my-class
    fun parseResponse(response: Response): List<WorcoutListEntity.ExerciseEntity>
    {
        val body = response.body?.string() ?: ""
        var gson = Gson()

        val type = object : TypeToken<List<WorcoutListEntity.ExerciseEntity>>() {}.type
        val result: List<WorcoutListEntity.ExerciseEntity> =
            parseArray<List<WorcoutListEntity.ExerciseEntity>>(json = body, typeToken = type)
        println(result)

        worcoutListEntity = result
        return result
    }

}



