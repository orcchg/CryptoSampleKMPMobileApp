package com.orcchg.crypto.sample.mobileapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform