package net.oneread.oneread.util.extension

/**
 * ---------------------------------------------------
 * Created by Sermilion on 28/07/2017.
 * Project: OneRead
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */
fun validateEmail(email: String)
        = email.contains("@")
        && email.contains(".")
        && email.length > 5

fun validatePassword(password: String)
        = password.matches(Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+\$"))

fun validateUsername(username: String): Boolean {
    return !username.isEmpty() && username.length > 3 && username.length < 20

}