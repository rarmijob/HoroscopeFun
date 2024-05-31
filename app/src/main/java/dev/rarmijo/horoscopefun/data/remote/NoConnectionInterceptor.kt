package dev.rarmijo.horoscopefun.data.remote

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody

class NoConnectionInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val protocol = when (chain.request().url.scheme) {
            "http" -> Protocol.HTTP_1_1
            "https" -> Protocol.HTTP_2
            else -> Protocol.HTTP_1_1 // Default to HTTP/1.1 if scheme is unknown
        }

        val responseString ="Service Unavailable"

        // Simula una falta de conexión (por ejemplo, código de estado 503 Service Unavailable)
        return Response.Builder()
            .request(chain.request())
            .protocol(protocol)
            .code(503)
            .message(responseString)
            .body(
                responseString.toByteArray()
                    .toResponseBody(contentType = "application/json".toMediaTypeOrNull())
            )
            .build()
    }
}