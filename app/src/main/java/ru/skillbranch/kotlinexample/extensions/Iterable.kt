package ru.skillbranch.kotlinexample.extensions

fun <T> List<T>.dropLastUntil(predicate: (T) -> Boolean) :List<T> =
    when {
        !this.any(predicate) -> this
        else -> this.dropLast(1).dropLastUntil(predicate)
    }