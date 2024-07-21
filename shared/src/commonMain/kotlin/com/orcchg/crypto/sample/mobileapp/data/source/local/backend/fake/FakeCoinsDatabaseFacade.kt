package com.orcchg.crypto.sample.mobileapp.data.source.local.backend.fake

import com.orcchg.crypto.sample.mobileapp.data.source.local.CoinsDatabaseFacade
import com.orcchg.crypto.sample.mobileapp.data.source.local.model.mapper.CoinDaoToDomainMapper
import com.orcchg.crypto.sample.mobileapp.database.CoinDao
import com.orcchg.crypto.sample.mobileapp.domain.model.MoneySign
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.Clock

internal class FakeCoinsDatabaseFacade(
    facade: CoinsDatabaseFacade
) : CoinsDatabaseFacade by facade {

    init {
        runBlocking {
            val now = Clock.System.now().toEpochMilliseconds()
            listOf(
                CoinDao(
                    id = 0L,
                    name = "Bitcoin",
                    symbol = "BTC",
                    url = "https://bitcoin.org",
                    logoUrl = "https://raw.githubusercontent.com/trustwallet/assets/master/blockchains/bitcoin/info/logo.png",
                    price = "57881.6",
                    priceCurrency = "USD",
                    priceSign = MoneySign.PLUS,
                    delta = "279.51",
                    deltaCurrency = "USD",
                    deltaSign = MoneySign.PLUS,
                    isFavourite = true,
                    createdAt = now
                ),
                CoinDao(
                    id = 2L,
                    name = "Litecoin",
                    symbol = "LTC",
                    url = "https://litecoin.org",
                    logoUrl = "https://raw.githubusercontent.com/trustwallet/assets/master/blockchains/litecoin/info/logo.png",
                    price = "65.25",
                    priceCurrency = "USD",
                    priceSign = MoneySign.PLUS,
                    delta = "0.01",
                    deltaCurrency = "USD",
                    deltaSign = MoneySign.MINUS,
                    isFavourite = true,
                    createdAt = now
                ),
                CoinDao(
                    id = 3L,
                    name = "Dogecoin",
                    symbol = "DOGE",
                    url = "https://dogecoin.com",
                    logoUrl = "https://raw.githubusercontent.com/trustwallet/assets/master/blockchains/doge/info/logo.png",
                    price = "0.11",
                    priceCurrency = "USD",
                    priceSign = MoneySign.PLUS,
                    delta = "0.0001",
                    deltaCurrency = "USD",
                    deltaSign = MoneySign.PLUS,
                    isFavourite = false,
                    createdAt = now
                ),
                CoinDao(
                    id = 5L,
                    name = "Dash",
                    symbol = "DASH",
                    url = "https://dash.org",
                    logoUrl = "https://raw.githubusercontent.com/trustwallet/assets/master/blockchains/dash/info/logo.png",
                    price = "23.39",
                    priceCurrency = "USD",
                    priceSign = MoneySign.PLUS,
                    delta = "0.24",
                    deltaCurrency = "USD",
                    deltaSign = MoneySign.MINUS,
                    isFavourite = true,
                    createdAt = now
                ),
                CoinDao(
                    id = 14L,
                    name = "Viacoin",
                    symbol = "VIA",
                    url = "https://viacoin.org",
                    logoUrl = "https://raw.githubusercontent.com/trustwallet/assets/master/blockchains/viacoin/info/logo.png",
                    price = "0.3492",
                    priceCurrency = "USD",
                    priceSign = MoneySign.PLUS,
                    delta = "0.03",
                    deltaCurrency = "USD",
                    deltaSign = MoneySign.PLUS,
                    isFavourite = false,
                    createdAt = now
                ),
                CoinDao(
                    id = 17L,
                    name = "Groestlcoin",
                    symbol = "GRS",
                    url = "https://www.groestlcoin.org",
                    logoUrl = "https://raw.githubusercontent.com/trustwallet/assets/master/blockchains/groestlcoin/info/logo.png",
                    price = "0.2534",
                    priceCurrency = "USD",
                    priceSign = MoneySign.PLUS,
                    delta = "0.007",
                    deltaCurrency = "USD",
                    deltaSign = MoneySign.PLUS,
                    isFavourite = false,
                    createdAt = now
                ),
                CoinDao(
                    id = 20L,
                    name = "DigiByte",
                    symbol = "DGB",
                    url = "https://www.digibyte.io",
                    logoUrl = "https://raw.githubusercontent.com/trustwallet/assets/master/blockchains/digibyte/info/logo.png",
                    price = "0.0074",
                    priceCurrency = "USD",
                    priceSign = MoneySign.PLUS,
                    delta = "0.0002",
                    deltaCurrency = "USD",
                    deltaSign = MoneySign.MINUS,
                    isFavourite = false,
                    createdAt = now
                ),
                CoinDao(
                    id = 22L,
                    name = "Monacoin",
                    symbol = "MONA",
                    url = "https://monacoin.org",
                    logoUrl = "https://raw.githubusercontent.com/trustwallet/assets/master/blockchains/monacoin/info/logo.png",
                    price = "0.28",
                    priceCurrency = "USD",
                    priceSign = MoneySign.PLUS,
                    delta = "0.08",
                    deltaCurrency = "USD",
                    deltaSign = MoneySign.PLUS,
                    isFavourite = true,
                    createdAt = now
                ),
                CoinDao(
                    id = 42L,
                    name = "Decred",
                    symbol = "DCR",
                    url = "https://decred.org",
                    logoUrl = "https://raw.githubusercontent.com/trustwallet/assets/master/blockchains/decred/info/logo.png",
                    price = "13.2",
                    priceCurrency = "USD",
                    priceSign = MoneySign.PLUS,
                    delta = "0.0051",
                    deltaCurrency = "USD",
                    deltaSign = MoneySign.MINUS,
                    isFavourite = true,
                    createdAt = now
                ),
                CoinDao(
                    id = 57L,
                    name = "Syscoin",
                    symbol = "SYS",
                    url = "https://syscoin.org",
                    logoUrl = "https://raw.githubusercontent.com/trustwallet/assets/master/blockchains/syscoin/info/logo.png",
                    price = "0.09816",
                    priceCurrency = "USD",
                    priceSign = MoneySign.PLUS,
                    delta = "0.00073",
                    deltaCurrency = "USD",
                    deltaSign = MoneySign.MINUS,
                    isFavourite = false,
                    createdAt = now
                ),
                CoinDao(
                    id = 60L,
                    name = "Ethereum",
                    symbol = "ETH",
                    url = "https://ethereum.org",
                    logoUrl = "https://raw.githubusercontent.com/trustwallet/assets/master/blockchains/ethereum/info/logo.png",
                    price = "3050.92",
                    priceCurrency = "USD",
                    priceSign = MoneySign.PLUS,
                    delta = "21.067",
                    deltaCurrency = "USD",
                    deltaSign = MoneySign.PLUS,
                    isFavourite = true,
                    createdAt = now
                ),
                CoinDao(
                    id = 61L,
                    name = "Ethereum Classic",
                    symbol = "ETC",
                    url = "https://ethereumclassic.org",
                    logoUrl = "https://raw.githubusercontent.com/trustwallet/assets/master/blockchains/classic/info/logo.png",
                    price = "21.09",
                    priceCurrency = "USD",
                    priceSign = MoneySign.PLUS,
                    delta = "0.0306",
                    deltaCurrency = "USD",
                    deltaSign = MoneySign.PLUS,
                    isFavourite = false,
                    createdAt = now
                ),
                CoinDao(
                    id = 74L,
                    name = "ICON",
                    symbol = "ICX",
                    url = "https://icon.foundation",
                    logoUrl = "https://raw.githubusercontent.com/trustwallet/assets/master/blockchains/icon/info/logo.png",
                    price = "0.159018",
                    priceCurrency = "USD",
                    priceSign = MoneySign.PLUS,
                    delta = "0.005801",
                    deltaCurrency = "USD",
                    deltaSign = MoneySign.MINUS,
                    isFavourite = false,
                    createdAt = now
                ),
            )
                .map(CoinDaoToDomainMapper::toDomain)
                .let { insert(it) }
        }
    }
}
