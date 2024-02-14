package com.mahesaiqbal.libraries.core

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform