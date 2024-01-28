package com.blitz.api

import android.annotation.SuppressLint
import okhttp3.OkHttpClient
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * Une classe pour tester l'api en local.
 */
class NetworkSecurity {
    fun createOkHttpClient(): OkHttpClient {
        val trustAllCertificates = TrustAllCertificates()
        val trustManagers: Array<TrustManager> = arrayOf(trustAllCertificates)
        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, trustManagers, SecureRandom())

        val trustAllHostnames = HostnameVerifier { _, _ -> true }

        return OkHttpClient.Builder()
            .hostnameVerifier(trustAllHostnames)
            .sslSocketFactory(sslContext.socketFactory, trustAllCertificates)
            .build()
    }
}

@SuppressLint("CustomX509TrustManager")
class TrustAllCertificates : X509TrustManager {
    @SuppressLint("TrustAllX509TrustManager")
    override fun checkClientTrusted(chain: Array<X509Certificate?>?, authType: String?) {
        // trust all client certificates
    }

    @SuppressLint("TrustAllX509TrustManager")
    override fun checkServerTrusted(chain: Array<X509Certificate?>?, authType: String?) {
        // trust all server certificates
    }

    override fun getAcceptedIssuers(): Array<X509Certificate> {
        return emptyArray()// trust all issuers
    }
}