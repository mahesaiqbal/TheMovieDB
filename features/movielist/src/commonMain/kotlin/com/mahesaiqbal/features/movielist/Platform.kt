package com.mahesaiqbal.features.movielist

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform