package org.example.filmoteka

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform