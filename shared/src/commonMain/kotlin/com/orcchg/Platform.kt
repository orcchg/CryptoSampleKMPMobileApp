package com.orcchg

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform