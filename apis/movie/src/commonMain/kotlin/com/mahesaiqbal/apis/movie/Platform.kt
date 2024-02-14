package com.mahesaiqbal.apis.movie

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform