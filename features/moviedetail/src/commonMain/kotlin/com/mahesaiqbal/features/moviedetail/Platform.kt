package com.mahesaiqbal.features.moviedetail

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform