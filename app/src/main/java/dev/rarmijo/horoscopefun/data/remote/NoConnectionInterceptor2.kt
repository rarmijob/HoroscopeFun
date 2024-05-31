package dev.rarmijo.horoscopefun.data.remote

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class NoConnectionInterceptor2 : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url.toUri().toString()
        val responseString = when {
            uri.endsWith("example/endpoint") -> {
                // Replace this with your mock JSON response
                """{"key": "value"}"""
            }
            else -> {
                """{"error": "Unknown endpoint"}"""
            }
        }

        return chain.proceed(chain.request())
            .newBuilder()
            .code(200)
            .protocol(okhttp3.Protocol.HTTP_1_1)
            .message(responseString)
            .body(
                responseString.toByteArray()
                    .toResponseBody(contentType = "application/json".toMediaTypeOrNull())
            )
            .build()
    }
}