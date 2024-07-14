package com.orcchg.crypto.sample.mobileapp.data

import com.orcchg.crypto.sample.mobileapp.data.backend.BackendPreferences
import com.orcchg.crypto.sample.mobileapp.data.http.DefaultHttpClientProvider
import com.orcchg.crypto.sample.mobileapp.data.http.HttpClientProvider
import com.orcchg.crypto.sample.mobileapp.data.model.CoinsPageEntity
import com.orcchg.crypto.sample.mobileapp.domain.CryptoRepository
import com.orcchg.crypto.sample.mobileapp.domain.model.CoinsPage
import com.orcchg.crypto.sample.mobileapp.domain.model.mapper.CoinsPageToDomainMapper
import io.ktor.client.call.body
import io.ktor.client.request.forms.submitForm
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.http.contentType

internal class RealCryptoRepository(
    private val backendPreferences: BackendPreferences,
    httpClientProvider: HttpClientProvider = DefaultHttpClientProvider()
) : CryptoRepository {

    private val client by lazy(LazyThreadSafetyMode.NONE) {
        httpClientProvider.httpClient()
    }

    override suspend fun coins(limit: Int, offset: Int): CoinsPage =
        client.submitForm(
            url = "${backendPreferences.baseUrl}/coins",
            formParameters = Parameters.build {
                append("limit", "$limit")
                append("offset", "$offset")
            },
            encodeInQuery = true // to use GET
        ) {
            contentType(ContentType.Application.FormUrlEncoded)
        }
            .body<CoinsPageEntity>()
            .let(CoinsPageToDomainMapper::map)
}
