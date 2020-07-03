package com.example.whatthefeast

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException


class DataReceiver {

    var client = OkHttpClient()
    private val key = 9973533
    private val url = "https://www.themealdb.com/api/json/v2/$key/randomselection.php"

    init {
        CoroutineScope(IO).launch {
            reqAPI()
        }

    }


    private suspend fun reqAPI(){
        val result = getRandomMeals(url)

        println(result)
    }

    @Throws(IOException::class)
    suspend fun getRandomMeals(url: String): String? {
        val request: Request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).execute().use {
                response -> return response.body!!.string()
        }
    }
}