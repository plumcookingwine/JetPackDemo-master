package com.plumcookingwine.jetpack

import android.os.Environment
import okhttp3.*
import org.junit.Assert.*
import org.junit.Test
import retrofit2.Retrofit
import java.io.File
import java.io.IOException
import java.net.URL


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @Test
    fun testRetrofit() {

        val retrofit = Retrofit.Builder()
            .build()
    }

    @Test
    fun testOkHttp() {

        // HttpURLConnection

        val  url =  URL("path")
        val connection = url.openConnection()
        connection.getInputStream()

        val cookieJar = object :CookieJar {
            override fun loadForRequest(url: HttpUrl): List<Cookie> {
                return mutableListOf()
            }

            override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
            }

        }

        val client = OkHttpClient.Builder()
            .cookieJar(cookieJar)
            .cache(Cache(File(Environment.DIRECTORY_DOCUMENTS), 1024))
            .retryOnConnectionFailure(false)
            .build()

        client.dispatcher.idleCallback = Runnable {
            println("请求完毕")
        }

        val request = Request.Builder()
            .get()
            .post(MyRequestBody())
            .url("https://wanandroid.com/wxarticle/chapters/json  ")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {


                println("异步请求${response.body?.string()}")

            }

        })

        val response = client.newCall(request)
            .execute()

        println("同步请求${response.body?.string()}")

        val test = testTryFinally()
        println(test)
    }

    private fun testTryFinally(): Int {

        try {
            Thread.sleep(1000)
            return 111
        } finally {
            println("222")
        }

    }
}