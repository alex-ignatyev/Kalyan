package ktor

const val PORT = ":8100"
expect fun getBaseUrl(): String
fun createBaseUrl(): String = getBaseUrl() + PORT