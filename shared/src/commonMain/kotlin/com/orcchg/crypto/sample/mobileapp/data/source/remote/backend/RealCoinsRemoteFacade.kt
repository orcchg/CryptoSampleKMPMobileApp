package com.orcchg.crypto.sample.mobileapp.data.source.remote.backend

import com.orcchg.crypto.sample.mobileapp.common.ioDispatcher
import com.orcchg.crypto.sample.mobileapp.data.source.remote.CoinsRemoteFacade
import com.orcchg.crypto.sample.mobileapp.data.source.remote.http.DefaultHttpClientProvider
import com.orcchg.crypto.sample.mobileapp.data.source.remote.http.HttpClientProvider
import com.orcchg.crypto.sample.mobileapp.data.source.remote.model.CoinsPageEntity
import com.orcchg.crypto.sample.mobileapp.domain.model.CoinsPage
import com.orcchg.crypto.sample.mobileapp.data.source.remote.model.mapper.CoinsPageToDomainMapper
import io.ktor.client.call.body
import io.ktor.client.request.forms.submitForm
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.http.contentType
import kotlinx.coroutines.withContext

internal class RealCoinsRemoteFacade(
    private val backendPreferences: BackendPreferences,
    httpClientProvider: HttpClientProvider = DefaultHttpClientProvider()
) : CoinsRemoteFacade {

    private val client by lazy(LazyThreadSafetyMode.NONE) {
        httpClientProvider.httpClient()
    }

    override suspend fun coins(offset: Int, limit: Int): CoinsPage =
        withContext(ioDispatcher()) {
            client.submitForm(
                url = "${backendPreferences.baseUrl}/${Endpoints.COINS_ENDPOINT}",
                formParameters = Parameters.build {
                    append(Endpoints.COINS_ENDPOINT_PARAM_LIMIT, "$limit")
                    append(Endpoints.COINS_ENDPOINT_PARAM_OFFSET, "$offset")
                },
                encodeInQuery = true // to use GET
            ) {
                contentType(ContentType.Application.FormUrlEncoded)
            }
                .body<CoinsPageEntity>()
                .let(CoinsPageToDomainMapper::map)
        }
}
