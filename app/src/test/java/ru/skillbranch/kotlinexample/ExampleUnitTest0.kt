package ru.skillbranch.kotlinexample

import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

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
}