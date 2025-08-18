package com.back.standard.extensions

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@OptIn(ExperimentalEncodingApi::class)
fun String.base64Encode(): String {
    return Base64.UrlSafe.encode(this.toByteArray())
}

@OptIn(ExperimentalEncodingApi::class)
fun String.base64Decode(): String {
    return Base64.UrlSafe.decode(this).decodeToString()
}

fun <T : Any> T?.getOrThrow(): T {
    return this ?: throw NoSuchElementException()
}