package ru.skillbranch.kotlinexample

import org.junit.Assert
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest0 {
    @Test
    fun test0() {
        val a = listOfNotNull("aaa", "bbb")
        val b = a.map { it.first().toUpperCase() }
        val c = b.joinToString(" ")

        println(a)
        println(b)
        println(c)
    }

    @Test
    fun test1() {
        val holder = UserHolder
        val user = holder.registerUserByPhone("John Doe", "+7 (917) 971-11-11")

        holder.requestAccessCode("+7 (917) 971-11-11")

        val successResult =  holder.loginUser("+7 (917) 971-11-11", user.accessCode!!)

        println(successResult)
    }

    @Test
    fun requestAccessCode_user() {
        val holder = UserHolder
        val user = holder.registerUserByPhone("John Doe", "+7 (917) 971 11-11")



        val successResult = holder.loginUser("john_doe@unknown.com", "testPass")

        println(successResult)
    }

    @Test
    fun register_user() {
        val holder = UserHolder
        holder.registerUser("John Doe", "John_Doe@unknown.com", "testPass")
        val expectedInfo = """
            firstName: John
            lastName: Doe
            login: john_doe@unknown.com
            fullName: John Doe
            initials: J D
            email: John_Doe@unknown.com
            phone: null
            meta: {auth=password}            
        """.trimIndent()

        val failResult = holder.loginUser("John_Doe@unknown.com", "testPass")
        val successResult = holder.loginUser("john_doe@unknown.com", "testPass")

        Assert.assertEquals(null, failResult)
        Assert.assertEquals(expectedInfo, successResult)
    }

    @Test
    fun importUsers_test() {
        val holder = UserHolder

        val list = listOf(
            "Andrey Mihaylenko;adigital@yandex.com;[a@7591083d:a6adb4becdc64e92857e1e2a0fd6af84;+7(918)-087 8071;",
            " John Doe ;JohnDoe@unknow.com;[B@7591083d:c6adb4becdc64e92857e1e2a0fd6af84;;",
            //" John Doe ;john_doe@unknown.com;[B@77846d2c:78b2b89aa1513ffbe83c7672d8afe6e4;;",
            "John1 Doe1 ;JohnDoe1@unknow.com;[C@7591083d:d6adb4becdc64e92857e1e2a0fd6af84;;",
            " John2 Doe2 ;JohnDoe2@unknow.com;DB@7591083d:e6adb4becdc64e92857e1e2a0fd6af84;;",
            " John3 Doe3 ;JohnDoe3@unknow.com;EB@7591083d:f6adb4becdc64e92857e1e2a0fd6af84;;"
        )

        val users = holder.importUsers(list)

        val successResult =  holder.loginUser("john_doe@unknown.com", "testPass")

//        println(successResult)

//        println(users.toString())
    }


}