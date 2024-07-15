package com.orcchg.crypto.sample.mobileapp.data.repository

import androidx.paging.PagingState
import app.cash.paging.PagingSource
import com.orcchg.crypto.sample.mobileapp.data.backend.BackendPreferences
import com.orcchg.crypto.sample.mobileapp.data.http.DefaultHttpClientProvider
import com.orcchg.crypto.sample.mobileapp.data.http.HttpClientProvider
import com.orcchg.crypto.sample.mobileapp.data.model.CoinsPageEntity
import com.orcchg.crypto.sample.mobileapp.domain.model.PricedCoin
import com.orcchg.crypto.sample.mobileapp.domain.model.mapper.CoinsPageToDomainMapper
import io.ktor.client.call.body
import io.ktor.client.request.forms.submitForm
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.http.contentType

internal class CoinsPagingSource(
    private val backendPreferences: BackendPreferences,
    httpClientProvider: HttpClientProvider = DefaultHttpClientProvider()
) : PagingSource<Int, PricedCoin>() {

    private val client by lazy(LazyThreadSafetyMode.NONE) {
        httpClientProvider.httpClient()
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PricedCoin> =
        runCatching {
            val offset = params.key ?: 0
            client.submitForm(
                url = "${backendPreferences.baseUrl}/$COINS_ENDPOINT",
                formParameters = Parameters.build {
                    append(COINS_ENDPOINT_PARAM_LIMIT, "$PAGE_LIMIT")
                    append(COINS_ENDPOINT_PARAM_OFFSET, "$offset")
                },
                encodeInQuery = true // to use GET
            ) {
                contentType(ContentType.Application.FormUrlEncoded)
            }
                .body<CoinsPageEntity>()
                .let(CoinsPageToDomainMapper::map)
                .let { p ->
                    LoadResult.Page(
                        data = p.coins,
                        prevKey = (offset - PAGE_LIMIT).takeUnless { it < 0 },
                        nextKey = (offset + PAGE_LIMIT).takeUnless { it >= p.total },
                        itemsBefore = offset.coerceAtLeast(0),
                        itemsAfter = (p.total - offset).coerceAtLeast(0)
                    )
                }
        }
            .getOrElse { e ->
                LoadResult.Error(e)
            }

    override fun getRefreshKey(state: PagingState<Int, PricedCoin>): Int? =
        (state.anchorPosition ?: 0)
            .takeIf { state.pages.size > it }
            ?.let { anchorPosition ->
                ((state.pages[anchorPosition].nextKey ?: 0) - PAGE_LIMIT.rotateRight(1))
                    .coerceAtLeast(0)
            }
            ?: 0

    companion object {
        private const val COINS_ENDPOINT = "coins"
        private const val COINS_ENDPOINT_PARAM_LIMIT = "limit"
        private const val COINS_ENDPOINT_PARAM_OFFSET = "offset"
        private const val PAGE_LIMIT = 30
    }
}
