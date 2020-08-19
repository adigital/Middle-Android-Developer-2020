package ru.skillbranch.skillarticles.extensions

fun String?.indexesOf(substr: String, ignoreCase: Boolean = true): List<Int> {
    val listOut: MutableList<Int> = mutableListOf()

    if (!this.isNullOrEmpty() && substr.isNotEmpty()) {
        var matchIndex: Int = -1
        do {
            matchIndex = this.indexOf(substr, matchIndex + 1, ignoreCase)
            if (matchIndex != -1) listOut.add(matchIndex)
        } while (matchIndex != -1)
    }

    return listOut
}