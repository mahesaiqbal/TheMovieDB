package com.mahesaiqbal.libraries.component

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform