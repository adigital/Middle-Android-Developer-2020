package ru.skillbranch.kotlinexample.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String, String?>{
        val parts: List<String>? = fullName?.split(" ")
        val firstName = parts?.getOrNull(0)?.takeUnless { it.isEmpty() }.apply { this }
        val lastName = parts?.getOrNull(1)?.takeUnless { it.isEmpty() }.apply { this }

        return firstName.toString() to lastName
    }
}