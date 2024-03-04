package com.antonprokopov.core.extensions

fun String.toLongRepresentation() = toCharArray().map { it.code }.joinToString().toLong()