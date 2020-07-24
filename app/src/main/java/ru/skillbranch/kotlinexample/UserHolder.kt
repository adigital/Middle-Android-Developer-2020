package ru.skillbranch.kotlinexample

import androidx.annotation.VisibleForTesting
import ru.skillbranch.kotlinexample.utils.Utils.parseFullName

object UserHolder {
    private val map = mutableMapOf<String, User>()

    fun registerUser(fullName: String, email: String, password: String): User =
        User.makeUser(fullName, email = email, password = password).also { user ->
            if (map.containsKey(user.login)) throw IllegalArgumentException("A user with this email already exists")
            else map[user.login] = user
        }

    fun registerUserByPhone(fullName: String, phone: String): User =
        User.makeUser(fullName, null, null, phone = checkLogin(phone)).also { user ->
            if (map.containsKey(user.login)) throw IllegalArgumentException("A user with this phone already exists")
            else map[user.login] = user
        }

    fun loginUser(login: String, password: String): String? =
        map[checkLogin(login)]?.let {
            if (it.checkPassword(password)) it.userInfo
            else null
        }

    fun requestAccessCode(login: String) : Unit {
        val user = map[checkLogin(login)]
        user?.changePassword(user.accessCode!!, user.generateAccessCode())
    }

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    fun clearHolder() {
        map.clear()
    }

    private fun checkLogin(login: String): String? {
        if (login.contains('@')) { // email
            return  login.trim().toLowerCase()
        } else { // phone
            if (login.contains("""[a-zA-Zа-яА-Я]""".toRegex()) || !login.replace("""[^+\d]""".toRegex(), "").matches("""^(\+)\d{11}""".toRegex())) {
                throw IllegalArgumentException("Enter a valid phone number starting with a + and containing 11 digits")
            }
            else {
               return login.replace("""[^+\d]""".toRegex(), "")
            }
        }
    }

    fun importUsers(list: List<String>): List<User>? {
         list.forEach {
             val line = it.split(';')

             val fullName = parseFullName(line[0].trim())
             val email = line[1].ifEmpty {  null }
             val salt = line[2].split(':')[0].ifEmpty { null }
             val hash = line[2].split(':')[1].ifEmpty { null }
             val phone = line[3].ifEmpty {  null }

             val user = User(fullName.first, fullName.second, email, salt, hash, phone)

             if (map.containsKey(user.login)) throw IllegalArgumentException("A user with this login already exists")
             else map[user.login] = user
         }

        return map.values.toMutableList()
    }
}