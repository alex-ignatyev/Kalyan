package ktor

const val PORT = ":8080"
expect fun getBaseUrl(): String
fun createBaseUrl(): String = getBaseUrl() + PORT